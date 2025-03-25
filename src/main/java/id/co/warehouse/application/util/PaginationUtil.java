package id.co.warehouse.application.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginationUtil {

    public static Pageable setPagination(Integer pageNumber, Integer pageSize) {
        return PageRequest.of(pageNumber, pageSize);
    }
}
