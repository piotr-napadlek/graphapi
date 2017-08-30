package io.napadlek.graphapi.business.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PagingDO {
    private CursorsDO cursors;

    public CursorsDO getCursors() {
        return cursors;
    }

    public void setCursors(CursorsDO cursors) {
        this.cursors = cursors;
    }
}
