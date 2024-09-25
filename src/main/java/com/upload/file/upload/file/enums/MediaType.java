package com.upload.file.upload.file.enums;

import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MediaType {
	
    PDF("application/pdf"),
    JPEG("image/jpeg"),
    PNG("image/png"),
    GIF("image/gif"),
    HTML("text/html"),
    CSS("text/css"),
    JSON("application/json"),
    XML("application/xml"),
    MP3("audio/mpeg"),
    MP4("video/mp4"),
    ZIP("application/zip"),
    TXT("text/plain"),
    DOC("application/msword"),           // Word 97-2003
    DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"), // Word 2007+
    XLS("application/vnd.ms-excel"),     // Excel 97-2003
    XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // Excel 2007+

	private String mimeType;
	
	public static MediaType getMediaType(String mimeType) {
		return Stream.of(MediaType.values())
				.filter(mediaType -> mediaType.mimeType.equalsIgnoreCase(mimeType))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("O formato de arquivo não é suportado."));
	}
}
