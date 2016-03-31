package JTable;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class newTable extends JFrame {
	JButton openButton = new JButton("Open File");
	public newTable() {

		try {
			createUI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("No file was found!");
		}

	}

	private void createUI() throws IOException {
		setBounds(50, 50, 800, 500);
		getContentPane().setLayout(null);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Object[] colNames = { "Date", "Color", "Breed", "Sex", "State", "Name", "DateCreated" };
		CSVReader reader = new CSVReader(new FileReader("CVS/animals.lostandfound.csv"));
		List entries = reader.readAll();
		DefaultTableModel tableModel = new DefaultTableModel(colNames, entries.size() - 1);
		int rowCount = tableModel.getRowCount();

		for (int x = 0; x < rowCount + 1; x++) {
			int columnnumber = 0;
			if (x > 0) {
				for (String thiscellvalue : (String[]) entries.get(x)) {
					tableModel.setValueAt(thiscellvalue, x - 1, columnnumber);
					columnnumber++;
				}
			}

		}
		JTable table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 13, 577, 246);
		getContentPane().add(scrollPane);

		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				int selectRow = table.getSelectedRow();
				if (selectRow != -1) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.removeRow(selectRow);

				}

				removeButton();

			}
		});
		removeButton.setBounds(601, 13, 97, 36);
		getContentPane().add(removeButton);

		JButton saveButton = new JButton("SAVE");
		saveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					CSVWriter writer = new CSVWriter(new FileWriter("CVS/newFile.csv"), ',');

					for (int loopOverTable = 0; loopOverTable < table.getRowCount(); loopOverTable++) {
						String[] rowData = new String[table.getColumnCount()];
						for (int getValueLoop = 0; getValueLoop < table.getColumnCount(); getValueLoop++) {
							rowData[getValueLoop] = (String) table.getValueAt(loopOverTable, getValueLoop);
						}

						// String[] entries = rowData;
						writer.writeNext(rowData);
					}

					writer.close();
				} catch (IOException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
				saveButton();
			}
		});
		saveButton.setBounds(601, 62, 97, 36);
		getContentPane().add(saveButton);
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openButton();
			}
		});
		
		
		openButton.setBounds(22, 272, 97, 25);
		getContentPane().add(openButton);
		
		reader.close();
	}

	public void removeButton() {

		JOptionPane.showMessageDialog(null, "Selected Entry has been removed!", "Entry Removed",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void saveButton() {

		JOptionPane.showMessageDialog(null, "List Updated", "New File Saved", JOptionPane.INFORMATION_MESSAGE);
	}
	private void openButton(){
	JFileChooser save = new JFileChooser();
	int returnVal = save.showOpenDialog(openButton);
}
}