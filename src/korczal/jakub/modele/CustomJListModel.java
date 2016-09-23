package korczal.jakub.modele;

import java.util.List;

import javax.swing.AbstractListModel;

public class CustomJListModel extends AbstractListModel<String>
{
	private List<String> items;
	
	public CustomJListModel(List<String> items)
	{
		this.items = items;
	}
	
	public void update(List<String> items)
	{
		this.items = items;
	}

	@Override
	public String getElementAt(int idx)
	{
		return items.get(idx); 
	}

	@Override
	public int getSize()
	{
		return items.size();
	}

}
