package com.upload.file.upload.file.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upload.file.upload.file.service.FileService;
import com.upload.file.upload.file.vo.ArquivoVO;

import lombok.RequiredArgsConstructor;

@RequestMapping("/files")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class FileController {
	
	private final FileService fileService;
	
	@PostMapping
	public String upload(@RequestHeader(name = "file") MultipartFile file) throws IOException {
		return fileService.upload(file);
	}
	
	@GetMapping("/{uuid}")
	public ResponseEntity<Resource> download(@PathVariable UUID uuid) throws IOException {
		
		ArquivoVO arquivo = fileService.download(uuid);
		
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, String.format("inline; filename=%s", arquivo.getNome()));
        headers.add(HttpHeaders.CONTENT_TYPE, arquivo.getTipo().getMimeType());
        
        InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(arquivo.getBytes()));
		
        return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
	}
}
