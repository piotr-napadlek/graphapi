package io.napadlek.graphapi.business.data;

import java.util.List;
import java.util.Optional;

public class PlaceSearchResultDO  {
    private List<PlaceInfoDO> data;
    private PagingDO paging;

    public List<PlaceInfoDO> getData() {
        return data;
    }

    public void setData(List<PlaceInfoDO> data) {
        this.data = data;
    }

    public PagingDO getPaging() {
        return paging;
    }

    public void setPaging(PagingDO paging) {
        this.paging = paging;
    }

    public Optional<String> getAfterCursor() {
        return Optional.ofNullable(paging).map(PagingDO::getCursors).map(CursorsDO::getAfter);
    }
}
