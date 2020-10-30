package com.cloudpacks.site.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.cloudpacks.site.model.User;
import com.cloudpacks.site.service.SecurityService;
import com.cloudpacks.site.service.UserService;
import com.cloudpacks.site.validator.UserValidator;

@Controller
public class UserController {

	@Value("${accesskey}")
	String accesskey;
	@Value("${secretkey}")
	String secretkey;
	@Value("${bucketName}")
	String bucketName;
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
			@RequestParam("photo") MultipartFile image) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}
		BasicAWSCredentials cred = new BasicAWSCredentials(accesskey, secretkey);
		// AmazonS3Client client=AmazonS3ClientBuilder.standard().withCredentials(new
		// AWSCredentialsProvider(cred)).with
		AmazonS3 client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(cred))
				.withRegion(Regions.US_EAST_1).build();
		try {
			PutObjectRequest put = new PutObjectRequest(bucketName, image.getOriginalFilename(), image.getInputStream(),
					new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead);
			client.putObject(put);

			String imgSrc = "http://" + bucketName + ".s3.amazonaws.com/" + image.getOriginalFilename();

			userForm.setImgurl(imgSrc);

			// Save this in the DB.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		userService.save(userForm);

		// securityService.autoLogin(userForm.getUsername(),
		// userForm.getPasswordConfirm());

		return "redirect:/welcome";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");
		model.addAttribute("userForm", new User());

		return "login";
	}

	@GetMapping("/index")
	public String landingpage(Model model, String error, String logout) {

		return "index";
	}

	@PostMapping("/welcome")
	public String checklogin(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

		userValidator.validateLogin(userForm, bindingResult);
		// securityService.autoLogin(userForm.getUsername(),
		// userForm.getPasswordConfirm());
		// uname=userForm.getUsername();
		if (bindingResult.hasErrors()) {
			return "login";
		} else {
			return "redirect:/welcome";
		}
	}

	@GetMapping({ "/", "/welcome" })
	public ModelAndView addorders() {
		User user = userService.findByUsername(userValidator.username);

		ModelAndView modelandview = new ModelAndView("welcome");
		System.out.println();
		modelandview.addObject("viewName", user.getName());
		modelandview.addObject("viewUsername", user.getUsername());
		modelandview.addObject("viewBiography", "      " + user.getBiography());
		modelandview.addObject("viewImageLink", user.getImgurl());

		return modelandview;
	}

	@GetMapping("/editProfile")
	public ModelAndView edit(Model model) {
		User user = userService.findByUsername(userValidator.username);
		model.addAttribute("userForm", user);
		ModelAndView modelandview = new ModelAndView("editProfile");
		modelandview.addObject("viewName", user.getName());
		modelandview.addObject("viewUsername", user.getUsername());
		modelandview.addObject("viewBiography", "      " + user.getBiography());
		modelandview.addObject("viewImageLink", user.getImgurl());
		return modelandview;
	}

	@PostMapping("/editProfile")
	public String editprofile(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model,
			@RequestParam("photo") MultipartFile image) {
		// userValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "welcome";
		}
		User user = userService.findByUsername(userValidator.username);

		user.setName(userForm.getName());
		user.setBiography(userForm.getBiography());
		userForm.setPassword(user.getPassword());
		BasicAWSCredentials cred = new BasicAWSCredentials(accesskey, secretkey);

		AmazonS3 client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(cred))
				.withRegion(Regions.US_EAST_1).build();
		try {

			if (!image.getOriginalFilename().isEmpty() && image.getOriginalFilename().length() > 0) {
				PutObjectRequest put = new PutObjectRequest(bucketName, image.getOriginalFilename(),
						image.getInputStream(), new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead);
				client.putObject(put);
				String imgSrc = "http://" + bucketName + ".s3.amazonaws.com/" + image.getOriginalFilename();
				user.setImgurl(imgSrc);
			} 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		userService.save1(user);
		return "redirect:/welcome";
	}

}
