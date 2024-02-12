package com.ktds.sport.debate.dto;

import com.ktds.sport.debate.domain.Post;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Page;

@Data
public class PageResponse {

    private int totalPage;

    private int page;

    private int size;

    private int start, end;

    private boolean prev, next;

    private List<Integer> pageList;

    public static PageResponse of(Page<Post> page) {
        PageResponse pageResponse = new PageResponse();

        pageResponse.setTotalPage(page.getTotalPages());
        pageResponse.setPage(page.getNumber() + 1); // Page 번호는 0부터 시작하므로 1을 더해줍니다.
        pageResponse.setSize(page.getSize());

        int currentPage = page.getNumber() + 1;
        int totalPages = page.getTotalPages();

        int displayPage = 5; // 페이지 목록에 표시할 페이지 수
        int pageGroup = (int) Math.ceil((double) currentPage / displayPage);

        int start = (pageGroup - 1) * displayPage + 1;
        int end = Math.min(start + displayPage - 1, totalPages);

        pageResponse.setStart(start);
        pageResponse.setEnd(end);
        pageResponse.setPrev(page.getNumber() + 1 > 1);
        pageResponse.setNext(page.getNumber() + 1 < totalPages);

        List<Integer> pageList = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            pageList.add(i);
        }
        pageResponse.setPageList(pageList);

        return pageResponse;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("page", page)
            .append("size", size)
            .append("start", start)
            .append("end", end)
            .append("prev", prev)
            .append("next", next)
            .append("totalPage", totalPage)
            .toString();
    }

}
