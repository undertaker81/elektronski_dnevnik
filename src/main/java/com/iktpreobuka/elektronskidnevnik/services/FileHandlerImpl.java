package com.iktpreobuka.elektronskidnevnik.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class FileHandlerImpl implements FileHandler{

	private static String UPLOAD_FOLDER = "C:\\SpringTemp\\";
	@Override
	public String singleFileUpload(MultipartFile file,RedirectAttributes redirectAttributes) {
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select file to upload!");
			return "redirect: uploadStatus";
		}
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			redirectAttributes.addFlashAttribute("message", "File" + file.getOriginalFilename() + "succsessfully uploaded");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
