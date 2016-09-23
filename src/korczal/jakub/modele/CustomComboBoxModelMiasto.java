package korczal.jakub.modele;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class CustomComboBoxModelMiasto extends AbstractListModel<String> implements ComboBoxModel<String>
{
	List<String> miastaList;
	String selectedItem;
	
	public CustomComboBoxModelMiasto(List<String> miastaList)
	{
		updateCustomComboBoxModelMiasto(miastaList);
		selectedItem = miastaList.get(0);
	}
	
	public void updateCustomComboBoxModelMiasto(List<String> miastaList)
	{
		if (miastaList == null || miastaList.isEmpty())
		{
			throw new IllegalArgumentException("PUSTA LISTA DLA MODELU COMBOBOX");
		}
		
		this.miastaList = miastaList;
	}
	

	@Override
	public String getElementAt(int idx)
	{
		return miastaList.get(idx);
	}

	@Override
	public int getSize()
	{
		return miastaList.size();
	}

	@Override
	public Object getSelectedItem()
	{
		return selectedItem;
	}

	@Override
	public void setSelectedItem(Object anItem)
	{
		selectedItem = (String)anItem;
		
	}

}
