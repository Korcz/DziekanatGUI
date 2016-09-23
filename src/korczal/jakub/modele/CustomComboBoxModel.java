package korczal.jakub.modele;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

//wlasny model tworzysz najczesciej w oparciu o klase, ktora reprezentuje ny model komponentu
public class CustomComboBoxModel extends AbstractListModel<Integer> implements ComboBoxModel<Integer>
{
	//opieramy model na liscie elementow typu Integer - bo poszczegolne elementy sa typu Integer
	List<Integer> idsList;
	Integer selectedItem; //bedzie przechowywac osttanio zaznaczony element w combobox
	
	public CustomComboBoxModel(List<Integer> idsList)
	{
		if (idsList == null || idsList.isEmpty())
		{
			throw new IllegalArgumentException("PUSTA LISTA DLA MODELU COMBOBOX");
		}
		updateCustomComboBoxModel(idsList);
		selectedItem = idsList.get(0); //od razu na poczatku bedziesz widzial pierwszy element combobox
	}
	
	public void updateCustomComboBoxModel(List<Integer> idsList)
	{
		this.idsList = idsList;
	}
	
	@Override
	public Integer getElementAt(int idx)
	{
		return idsList.get(idx);
	}

	@Override
	public int getSize()
	{
		return idsList.size();
	}

	

	@Override
	public Object getSelectedItem()
	{
		return selectedItem;
	}

	@Override
	public void setSelectedItem(Object anItem)
	{
		selectedItem = (Integer)anItem;
		
	}
	
}
