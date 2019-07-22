package com.LearnToCrypt.FileToString;

import org.springframework.web.multipart.MultipartFile;

public interface IFileToString {
	String getStringFromFile(MultipartFile file);
}
