package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.dao.memberDao;
import com.example.demo.vo.memberVo;

@Service
public class memberServiceImpl implements memberService {
	
	@Autowired
	memberDao memberDao;
	
	@Value("${file.upload.url}")
	private String fileUploadUrl;
	
	@Override
	public void insertMember(MultipartHttpServletRequest multiRequest, memberVo vo) throws Exception, IOException {
		Map<String, MultipartFile> files = multiRequest.getFileMap();
        // files.entrySet()의 요소를 가져온다.
        Iterator<Map.Entry< String, MultipartFile>> itr = files.entrySet().iterator();
        // MultipartFile 초기화
        MultipartFile mFile = null;
        // 파일이 업로드 될 경로를 지정
        String filePath = fileUploadUrl+"/profile";
        // 읽어 올 요소가 있으면 true, 없으면 false를 반환
        while (itr.hasNext()) {
            // Iterator의 MultipartFile 요소를 가져온다.
            Map.Entry<String, MultipartFile> entry = itr.next();

            // entry에 값을 가져온다.
            mFile = entry.getValue();
            // 원본 파일명, 저장 될 파일명 생성
            String fileOrgName = mFile.getOriginalFilename();

            if (!fileOrgName.isEmpty()) {
                // filePath에 해당되는 파일의 File 객체를 생성
                File fileFolder = new File(filePath);

                if (!fileFolder.exists()) {
                    // 부모 폴더까지 포함하여 경로에 폴더 생성
                    if (fileFolder.mkdirs()) {
                        System.out.println("[file.mkdirs] : Success");
                    }
                }

                File saveFile = new File(filePath, fileOrgName);

                // 생성한 파일 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로 삭제되기 때문에
                // transferTo(File f) 메서드를 이용해서 업로드 처리
                mFile.transferTo(saveFile);
                vo.setUser_image(fileOrgName);
            }
        }
		
		memberDao.insertMember(vo);
	}
	
	@Override
	public memberVo memberLogin(memberVo vo) {
		return memberDao.memberLogin(vo);
	}
	
	@Override
	public int checkId(memberVo vo) {
		return memberDao.checkId(vo);
	}

	@Override
	public void updateMember(memberVo vo) {
		memberDao.updateMember(vo);
	}
}
