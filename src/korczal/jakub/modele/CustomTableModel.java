package korczal.jakub.modele;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import korczak.jakub.klasy.Laczona;

public class CustomTableModel extends AbstractTableModel{

	private List<Laczona> rows;
	private List<String> columns;
	
	public CustomTableModel(List<Laczona> rows, List<String> columns) {
		this.rows = rows;
		this.columns = columns;
	}
	
	public void updateRows(List<Laczona> rows)
	{
		this.rows = rows;
	}
	
	@Override
	public int getColumnCount() {
		return columns.size();
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public Object getValueAt(int rowIdx, int columnIdx) {
		Laczona row = rows.get(rowIdx);
		
		if (columnIdx == 0)
		{
			return row.getId();
		}
		else if (columnIdx == 1)
		{
			return row.getImie();
		}
		else if (columnIdx == 2) {
			return row.getNazwisko();
		}
		else if (columnIdx == 3) {
			return row.getWiek();
		}
		else if (columnIdx == 4) {
			return row.getRokStudiow();
		}
		else if (columnIdx == 5) {
			return row.getName();
		}
		else if (columnIdx == 6) {
			return row.getPlace();
		}
		else if (columnIdx == 7) {
			return row.getDeansName();
		}
		else {
			return row.getEstablishYear();
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return columns.get(column);
	}

}
