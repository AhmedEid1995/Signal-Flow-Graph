package FrontEnd;

public class SelectedRowTable {
	private int selectedRow;
	private String tableName;

	public SelectedRowTable(int selectedRow, String tableName) {
		this.selectedRow = selectedRow;
		this.tableName = tableName;
	}

	public int getSelectedRow() {
		return selectedRow;
	}

	public String getTableName() {
		return tableName;
	}

}
