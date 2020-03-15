package cz.sedy.monitoringservice.controller.search;

import cz.sedy.monitoringservice.domain.MonitoringResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import net.kaczmarzyk.spring.data.jpa.utils.QueryContext;
import org.springframework.data.jpa.domain.Specification;

public class MonitoringResultSort implements Specification<MonitoringResult> {


    private static final String DESC_OPERATOR = "-";

    private static final long serialVersionUID = 1L;

    private static final Map<String, String> SEARCH_PARAM_MAP = Map.of(
            "createdAt", "createdAt"
    );


    protected List<String> orderValues;

    public MonitoringResultSort(QueryContext queryContext, String path, String[] httpParamValues) {
        this.orderValues = List.of(httpParamValues);
    }

    @Override
    public Predicate toPredicate(Root<MonitoringResult> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        query.orderBy(resolvePaths(root, cb));
        return null;
    }

    protected <F> Path<F> evaluate(Root<MonitoringResult> root, String value) {
        return root.get(value);
    }

    protected List<Order> resolvePaths(Root<MonitoringResult> root, CriteriaBuilder cb) {
        List<Order> orderList = new ArrayList<>();
        orderValues.forEach(
                orderValue -> {
                    if (orderValue.startsWith(DESC_OPERATOR)) {
                        if (SEARCH_PARAM_MAP.containsKey(orderValue.substring(1))) {
                            orderList.add(cb.desc(evaluate(root, mapFromSearchParamMap(orderValue.substring(1)))));
                        }
                    } else {
                        if (SEARCH_PARAM_MAP.containsKey(orderValue)) {
                            orderList.add(cb.asc(evaluate(root, mapFromSearchParamMap(orderValue))));
                        }
                    }
                }
        );
        return orderList;
    }

    protected String mapFromSearchParamMap(String queryParam) {
        return SEARCH_PARAM_MAP.get(queryParam);
    }
}
