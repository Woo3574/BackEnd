package com.kh.paikBookerMiniProjectJdbc.service;

import com.kh.paikBookerMiniProjectJdbc.dao.SearchDropDownDAO;
import com.kh.paikBookerMiniProjectJdbc.vo.StoreVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchDropDownSERVICE {
    private final SearchDropDownDAO searchDropDownDAO;

    // 매장 불러오는 조건 (store_addr을 "구" 형식으로 가공)
    public List<StoreVO> searchData(String region, String brandName, String reservationTime) {
        // 원본 매장 데이터를 가져옴
        List<StoreVO> storeList = searchDropDownDAO.searchData(region, brandName, reservationTime);

        // storeList에서 주소를 "구" 형식으로 변환
        storeList.forEach(store -> {
            String processedAddr = processRegion(store.getStoreAddr()); // 주소를 "구" 형식으로 가공
            store.setStoreAddr(processedAddr); // 가공된 주소를 다시 설정
        });

        return storeList; // 가공된 매장 리스트 반환
    }

    // 주소 데이터를 "구" 단위로 가공하는 메서드
    private String processRegion(String address) {
        if (address == null || address.isEmpty()) {
            return ""; // 주소가 없으면 빈 문자열 반환
        }

        // 주소에서 "구" 부분을 추출하기 위해 공백과 "구"를 기준으로 분리
        String[] parts = address.split(" "); // 공백 기준으로 분리
        for (String part : parts) {
            if (part.endsWith("구")) {
                return part; // "구"로 끝나는 부분을 반환
            }
        }
        return ""; // "구"가 없으면 빈 문자열 반환
    }


    //----------------------------------------------------------------------------------------


    // 카테고리 목록을 가져오는 메서드
    public Map<String, List<String>> getCategories() {
        Map<String, List<String>> categories = new HashMap<>();

        // 지역 목록 가공
        List<String> rawRegions = searchDropDownDAO.getRegions(); // 원본 주소 데이터
        List<String> processedRegions = processRegions(rawRegions); // 가공된 데이터

        categories.put("region", processedRegions); // 지역 목록
        categories.put("brandName", searchDropDownDAO.getBrandNames()); // 브랜드명 목록
        return categories;
    }

    // 주소 데이터를 "구" 단위로 가공하는 메서드
    private List<String> processRegions(List<String> rawRegions) {
        return rawRegions.stream()
                .map(addr -> extractRegion(addr)) // 주소에서 "구" 추출
                .distinct() // 중복 제거
                .sorted() // 정렬 (옵션)
                .collect(Collectors.toList());
    }

    // 주소에서 "구"를 추출하는 헬퍼 메서드
    private String extractRegion(String address) {
        // 주소에서 "구" 이전까지 추출
        int startIdx = address.indexOf(" ");
        int endIdx = address.indexOf("구");
        if (startIdx != -1 && endIdx != -1 && endIdx > startIdx) {
            return address.substring(startIdx + 1, endIdx + 1); // "구" 포함
        }
        return ""; // 형식이 맞지 않을 경우 빈 문자열 반환
    }
}
