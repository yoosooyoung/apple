package com.example.demo.service;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.dao.boardDao;
import com.example.demo.vo.FileVo;
import com.example.demo.vo.boardVo;


@Service
public class boardServiceImpl implements boardService {
	
	@Autowired
	boardDao boardDao;
	
	@Value("${file.upload.url}")
	private String fileUploadUrl;
	
	@Override
	public void uploadFile(MultipartHttpServletRequest multiRequest, FileVo fvo) throws Exception{
		 // 파일 정보를 값으로 하는 Map을 가져온다.
        Map<String, MultipartFile> files = multiRequest.getFileMap();
        // files.entrySet()의 요소를 가져온다.
        Iterator<Map.Entry< String, MultipartFile>> itr = files.entrySet().iterator();
        // MultipartFile 초기화
        MultipartFile mFile = null;
        // 파일이 업로드 될 경로를 지정
        String filePath = fileUploadUrl;
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
                fvo.setPicture_url(fileOrgName);
                boardDao.insertFile(fvo);
            }
        }
    }

	@Override
	public void insertBoard(boardVo bvo, FileVo fvo) throws Exception {
		String boardSeq = boardDao.getBoardSeq();
		bvo.setBoard_seq(boardSeq);
		fvo.setBoard_seq(boardSeq);
		boardDao.insertBoard(bvo);
	}

    @Override
    public void deleteBoard(boardVo bvo) throws Exception {
        // TO-DO 글 삭제시, 위시리스트에 있던 데이터도 삭제 && 좋아요 리스트에서 삭제
        boardDao.deleteBoard(bvo);
        boardDao.deletePicture(bvo);
    }

    @Override
    public void updateBoard(boardVo bvo) throws Exception {
        boardDao.updateBoard(bvo);
    }

    @Override
	public List<Map<String, Object>> listBoard() {
		
		List<Map<String, Object>> list = boardDao.listBoard();
		return list;
	}
}
	