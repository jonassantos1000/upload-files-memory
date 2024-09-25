package com.upload.file.upload.file.service;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.upload.file.upload.file.enums.MediaType;
import com.upload.file.upload.file.vo.ArquivoVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {
	
	private Map<UUID, ArquivoVO> files = new HashMap<>();
	
	public String upload(MultipartFile file) throws IOException {

		UUID uuid = UUID.randomUUID();
		
		ArquivoVO arquivoVO = new ArquivoVO(
				file.getOriginalFilename(),
				file.getResource().getInputStream().readAllBytes(),
				MediaType.getMediaType(file.getContentType()),
				Instant.now());
		
		files.put(uuid, arquivoVO);
		
		return uuid.toString();
	}
	
	public ArquivoVO download(UUID uuid) {
		return Optional.ofNullable(files.get(uuid)).orElseThrow(() -> new IllegalArgumentException("O arquivo não está mais disponível para download."));
	}
	
	@Scheduled(fixedDelay = 600000) // 10 min.
	private void limparMemoria() {
		for (Entry<UUID, ArquivoVO> teste: files.entrySet()) {
			if (teste.getValue().getDataCriado().plus(Duration.ofHours(1)).isBefore(Instant.now())) {
				files.remove(teste.getKey());
			}
		}
	}

}
