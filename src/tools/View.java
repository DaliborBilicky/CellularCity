package tools;

import enums.GridView;

public class View {
    private GridView gridView;
    private boolean underground;

    public View() {
        this.gridView = GridView.OVERGROUND;
    }

    public void setGridView(GridView view) {
        this.underground =
            !view.getStringValue().equals(GridView.OVERGROUND.getStringValue());
        this.gridView = view;
    }

    public GridView getView() {
        return this.gridView;
    }

    public boolean isUnderground() {
        return this.underground;
    }
}
