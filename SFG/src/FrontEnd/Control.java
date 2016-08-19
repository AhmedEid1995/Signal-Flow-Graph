package FrontEnd;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import BackEnd.Model;


public class Control extends MouseAdapter implements ActionListener {

	private Model model;
	private DrawPanel panel;
	private boolean addNewEdge;
	private boolean calculateTF;
	private boolean updateEdge;
	private View view;
	private LinkedList<BackEnd.Path> forwardPaths;
	private LinkedList<BackEnd.Path> loops;
	private SelectedRowTable tableSelection;
	private ArrayList<Edge> edges;
	private Edge addedEdge;
	private int edgeLabel;
	
	private static Control controller ;
	
	public static Control getInstance (DrawPanel panel, View ui){
		if(controller!=null){
			return controller;
		}else{
			controller=new Control( panel,  ui);
			return controller;
		}
		
	}

	private Control(DrawPanel panel, View ui) {
		this.panel = panel;
		this.view = ui;
		edgeLabel = 0;
		edges = new ArrayList<>();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cancelSelection();
		if (!updateEdge) {
			switch (e.getActionCommand()) {
			case "Add Node":
				addNewNode();
				break;
			case "Add Edge":
				addNewEdge = true;
				model = null;
				cancelAddinNewNode();
				break;
			case "Forward Paths":
				
				cancelAddingEdge();
				cancelAddinNewNode();
				break;
			case "Loops":
				
				cancelAddingEdge();
				cancelAddinNewNode();
				break;
			case "Calculate Gain":
				calculateTF = true;
				model = new Model(getNodes());
				cancelAddingEdge();
				break;
			}
		}
	}

	private void cancelAddinNewNode() {
		panel.cancelAddinNode();
	}

	private void cancelSelection() {
		if (tableSelection != null) {
			if (tableSelection.getTableName().equals("loopTable")) {
				Iterator<Node> iterator = loops
						.get(tableSelection.getSelectedRow()).getPath()
						.iterator();
				Node node;
				while (iterator.hasNext()) {
					node = iterator.next();
					node.setColor(panel.getDefaultNodeColor());
				}
			} else if (tableSelection.getTableName().equals("forwardTable")) {
				Iterator<Node> iterator = forwardPaths
						.get(tableSelection.getSelectedRow()).getPath()
						.iterator();
				Node node;
				while (iterator.hasNext()) {
					node = iterator.next();
					node.setColor(panel.getDefaultNodeColor());
				}
			}
			tableSelection = null;

			panel.repaint();
		}
	}

	private void cancelAddingEdge() {
		if (addNewEdge) {
			view.addLog("Adding Edge has not been completed ...");
			addNewEdge = false;

			panel.setSelectedNodeToNull();
		}
	}

	private void addNewNode() {
		if (ready()) {
			panel.creatNewNode();

			model = null;
			cancelAddingEdge();
		}
	}

	private boolean ready() {
		if (panel.isThereSelectedNode()) {
			JOptionPane.showMessageDialog(null,
					"End The previous Operation First !", "Alert",
					JOptionPane.ERROR_MESSAGE);

			view.addLog("Alert ! Uncompleted Operation.");
			return false;
		}
		return true;
	}

	private void addEdge(int x, int y) {
		if (!panel.isThereSelectedNode()) {// Nothing is selected

			Node node = panel.selectNode(x, y);
			if (node != null) {
				view.addLog("Start of The Edge has been Selected ...");
				addedEdge = new Edge(node, edgeLabel);
				node.addEdges(addedEdge);

				panel.repaint();
			}

		} else if (panel.isThereSelectedNode()) {
			Node node = panel.selectNode(x, y);
			if (node != null) {

				try {
					int value = Integer.parseInt(JOptionPane.showInputDialog(
							null, "Enter The Value of The Edge :", "New Edge",
							JOptionPane.DEFAULT_OPTION));

					edges.add(panel.addEdge(value));
					view.addEdge(new String[] { "e" + edgeLabel++, value + "" });
					addNewEdge = false;
					updateEdge = true;
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Input Value must be Integer !", "Error",
							JOptionPane.ERROR_MESSAGE);

					cancelAddingEdge();
				}

				view.addLog("Edge has been added .");
				panel.repaint();
			}

		}
	}

	private void printTF(int x, int y) {

		if (!panel.isThereSelectedNode()) {

			Node node = panel.selectNode(x, y);
			if (node != null) {
				view.addLog("R(s) has been Selected ...");

				panel.repaint();
			}

		} else if (panel.isThereSelectedNode()) {
			Node node = panel.selectNode(x, y);
			if (node != null) {
				view.setGainText(""
						+ model.getOverAllGain(panel.getSelectedNode(),
								panel.getSecondSelectedNode()));

				view.clearForwardData();
				forwardPaths = model.getForwardPaths();
				Iterator<BackEnd.Path> iterator = forwardPaths.iterator();
				BackEnd.Path path;
				int count = 1;
				while (iterator.hasNext()) {
					path = iterator.next();
					view.addForwardPaths(new String[] { "Path " + (count++),
							path.getGain() + "" });
				}

				view.clearLoopData();
				loops = model.getSingleNonTouchedLoops();
				iterator = loops.iterator();
				count = 1;
				while (iterator.hasNext()) {
					path = iterator.next();
					view.addLoop(new String[] { "Loop " + (count++),
							path.getGain() + "" });
				}

				calculateTF = false;
				view.addLog("C(s) has been Selected.");
				panel.setSelectedNodeToNull();
				panel.repaint();
			}

		}

	}


	private Node[] getNodes() {
		ArrayList<Node> list = panel.getNodes();
		Node[] nodes = new Node[list.size()];

		for (int i = 0; i < list.size(); i++) {
			nodes[i] = list.get(i);
			nodes[i].setLabel(i);
		}

		return nodes;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		cancelSelection();
		if (e.getSource() instanceof JTable) {
			JTable table = (JTable) e.getSource();

			int row = table.getSelectedRow();
			tableSelection = new SelectedRowTable(row, table.getName());
			if (table.getName().equals("loopTable")) {

				Iterator<Node> iterator = loops.get(row).getPath()
						.iterator();
				Node node;
				while (iterator.hasNext()) {
					node = iterator.next();
					node.setColor(Color.ORANGE);
				}
			} else if (table.getName().equals("forwardTable")) {
				Iterator<Node> iterator = forwardPaths.get(row).getPath()
						.iterator();
				Node node;
				while (iterator.hasNext()) {
					node = iterator.next();
					node.setColor(Color.ORANGE);
				}

			} else if (table.getName().equals("edgeTable")) {
				Edge edge = edges.get(row);
				int val = 0;
				try {
					val = Integer.parseInt(JOptionPane.showInputDialog(null,
							"Enter The Value of The Edge :", "New Edge",
							JOptionPane.DEFAULT_OPTION));

					edge.setValue(val);
					view.setEdgeValue(row, table.getSelectedColumn(), val);
				} catch (Exception ex) {
					edge.setValue(1);
					view.setEdgeValue(row, table.getSelectedColumn(), 1);
				}
			}
			panel.repaint();
		} else if (panel.isThereNewNode()) {
			panel.addNode();
			view.addLog("New Node Has Been Added...");
		} else if (updateEdge) {
			updateEdge = false;
			addedEdge = null;
		} else if (addNewEdge) {
			addEdge(e.getX(), e.getY());
		} else if (calculateTF) {
			printTF(e.getX(), e.getY());
		}

	}



	@Override
	public void mouseMoved(MouseEvent e) {
		if (updateEdge) {
			addedEdge.setSourcePointPosition(e.getX(),e.getY());
		} else if (panel.isThereNewNode()) {
			panel.setNewNode(e.getX(), e.getY());
		}

		panel.repaint();
	}

}
