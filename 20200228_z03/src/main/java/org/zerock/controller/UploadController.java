package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;



@Controller
@Log4j
@RequestMapping("/upload/*")
public class UploadController {

	private String uploadFolder = "C:\\upload";

	@GetMapping("/uploadAjax")
	public void exAjax() {
		
	}
	
	@GetMapping("/input")
	public void exFrom() {

	}

	@PostMapping("/uploadAjaxAction")
	public ResponseEntity<List<String>> uploadFormPost(MultipartFile[] uploadFile) {
		
		List<String> nameList = new ArrayList<>();
		
		for (MultipartFile multipartFile : uploadFile) {
			
			UUID uuid = UUID.randomUUID();
			
			log.info("-------------------------------------");
			log.info("Upload File Name: " + multipartFile.getOriginalFilename());
			log.info("Upload File Size: " + multipartFile.getSize());

//			File saveFile = new File(uploadFolder, multipartFile.getOriginalfname());//IE������ ���ϰ�ΰ� �ٵ���.
			String savefname = uuid.toString()+"_"+multipartFile.getOriginalFilename(); //�Ķ���;ȿ� ���� ���� ������ ����
			
			File saveFile = new File(uploadFolder, savefname);
			
			try {
				nameList.add("s_"+ savefname);
				
				multipartFile.transferTo(saveFile); // ������ ���ε� ���ش�.
				
				
				FileOutputStream thumbnail = 
						new FileOutputStream(new File(uploadFolder, "s_" + savefname));
				
						Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100); //100,100������� thumbnail������ ������ش�.

						thumbnail.close(); //�������� �������
						
			} catch (Exception e) {
				log.error(e.getMessage());
			} // end catch
		}//end for
		return new ResponseEntity<>(nameList, HttpStatus.OK);
	}

	@GetMapping("/display")	
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fname) {

		log.info("fname: " + fname);

		File file = new File("c:\\upload\\" + fname);

		log.info("file: " + file);

		ResponseEntity<byte[]> result = null;

		try {
			HttpHeaders header = new HttpHeaders();

			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
