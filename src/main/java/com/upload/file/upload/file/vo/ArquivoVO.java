package com.upload.file.upload.file.vo;

import java.time.Instant;

import com.upload.file.upload.file.enums.MediaType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArquivoVO {
	
	private String nome;
	private byte[] bytes;
	private MediaType tipo;
	private Instant dataCriado;

}
