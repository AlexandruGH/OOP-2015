package huffman.views;

import javax.swing.*;
import javax.swing.border.Border;
import huffman.views.CodeDisplay;
import java.awt.*;
import java.awt.event.*;
import huffman.models.Huffman;

public class Gui extends JFrame {
	private static final long serialVersionUID = -5669393605620836091L;
	private JTextArea inputMessageArea1, inputMessageArea2, outputMessageArea1, outputMessageArea2;
	private JButton button1, button2, button3;
	private JPanel inputArea1, inputArea1A, inputArea1B, inputArea1Ba, outputAreaSouth;
	private Huffman huffman;
	private DrawingWest drawTree;
	private CodeDisplay codeDisplay;
	private String inputText;
	private JScrollPane scroll1, scroll2, scroll3, scroll4, scroll5, scroll6, scroll7;
	private Border border;
	private Handler handler;
	private SouthArea1 southArea1;
	private SouthArea2 southArea2;
	private int clickCount = 0;

	public Gui() {
		super("Huffman simulation");
		this.setLayout(new BorderLayout());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		handler = new Handler();
		initializeNorthSection();
		initializeWestSection();
		initializeEastSection();
		initializeSouthSection();
		this.setVisible(true);
		JOptionPane.showMessageDialog(Gui.this,
				"To start enter the text and press the next button to apply the algorithm.", "Welcome",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void initializeNorthSection() {
		button1 = new JButton("Next");
		button1.addActionListener(handler);

		button2 = new JButton("Display encoded text");
		button2.addActionListener(handler);

		inputMessageArea1 = new JTextArea(3, 20);
		scroll1 = new JScrollPane(inputMessageArea1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		inputMessageArea1.setToolTipText("Please enter here the text");
		inputMessageArea1.setBorder(border);
		inputMessageArea1.setLineWrap(true);

		outputMessageArea1 = new JTextArea(3, 20);
		outputMessageArea1.setEditable(false);
		outputMessageArea1.setBorder(border);
		outputMessageArea1.setLineWrap(true);
		scroll2 = new JScrollPane(outputMessageArea1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		inputArea1A = new JPanel();
		inputArea1A.setLayout(new GridLayout(2, 2));
		inputArea1A.add(scroll1);
		inputArea1A.add(button1);
		inputArea1A.add(scroll2);
		inputArea1A.add(button2);

		button3 = new JButton("Decode");
		button3.addActionListener(handler);

		inputMessageArea2 = new JTextArea(3, 20);
		inputMessageArea2.setToolTipText("Please enter the encoding of the initial message.");
		inputMessageArea2.setLineWrap(true);
		scroll3 = new JScrollPane(inputMessageArea2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		outputMessageArea2 = new JTextArea(3, 20);
		outputMessageArea2.setEditable(false);
		outputMessageArea2.setLineWrap(true);
		scroll4 = new JScrollPane(outputMessageArea2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		inputArea1Ba = new JPanel();
		inputArea1Ba.setLayout(new GridLayout());
		inputArea1Ba.add(scroll3);
		inputArea1Ba.add(scroll4);

		inputArea1B = new JPanel();
		inputArea1B.setLayout(new GridLayout(1, 2));
		inputArea1B.add(button3);
		inputArea1B.add(inputArea1Ba);

		inputArea1 = new JPanel();
		inputArea1.setLayout(new GridLayout(1, 2));
		inputArea1.add(inputArea1A);
		inputArea1.add(inputArea1B);

		this.add(inputArea1, BorderLayout.NORTH);
	}

	private void initializeWestSection() {
		drawTree = new DrawingWest();
		scroll7 = new JScrollPane(drawTree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll7, BorderLayout.WEST);
	}

	private void initializeEastSection() {
		codeDisplay = new CodeDisplay();
		codeDisplay.setBorder(border);
		this.add(codeDisplay, BorderLayout.EAST);
	}

	private void initializeSouthSection() {
		outputAreaSouth = new JPanel();
		outputAreaSouth.setLayout(new GridLayout(1, 2));
		southArea1 = new SouthArea1(3, 20);
		southArea2 = new SouthArea2(3, 20);
		scroll5 = new JScrollPane(southArea1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll5.setBorder(border);
		scroll6 = new JScrollPane(southArea2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll6.setBorder(border);
		outputAreaSouth.add(scroll5);
		outputAreaSouth.add(scroll6);
		this.add(outputAreaSouth, BorderLayout.SOUTH);
	}

	private class Handler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == button1 && (clickCount == 0)) {
				inputText = inputMessageArea1.getText();
				if (inputText.equals("")) {
					JOptionPane.showMessageDialog(Gui.this, "Please enter some text.", "Error",
							JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
				inputMessageArea2.setText("");
				outputMessageArea1.setText("");
				outputMessageArea2.setText("");
				southArea1.clearArea();
				southArea2.clearArea();
				drawTree.clearTheWest();
				huffman = new Huffman(inputText);
				huffman.getSymbolsFrequency();
				codeDisplay.getFunction("frequency");
				clickCount++;
			} else if (event.getSource() == button1 && (clickCount == 1)) {
				huffman.buildHuffmanTree();
				codeDisplay.getFunction("huffmanTree");
				clickCount++;
			} else if (event.getSource() == button1 && (clickCount == 2)) {
				huffman.traverseHuffmanTree(huffman.getRootOfHuffmanTree(), "");
				codeDisplay.getFunction("traverseTree");
				clickCount++;
			} else if (event.getSource() == button1 && (clickCount == 3)) {
				huffman.encodeText();
				codeDisplay.getFunction("encode");
				clickCount++;
			} else if (event.getSource() == button1 && (clickCount == 4)) {
				huffman.determineMaximumHeight();
				codeDisplay.getFunction("determineMaximumHeight");
				clickCount++;
			} else if (event.getSource() == button1 && (clickCount == 5)) {
				huffman.determineNumberOfNodes();
				codeDisplay.getFunction("determineNumberOfNodes");
				clickCount++;
			} else if (event.getSource() == button1 && (clickCount == 6)) {
				drawTree.action(huffman);
				codeDisplay.getFunction("drawTree");
				southArea1.setEfficiencyResults(huffman);
				southArea2.displayEncodingCharacteristics(huffman);
				JOptionPane.showMessageDialog(Gui.this,
						"The simulation of Huffman tree construction ended.You can now decode and encode texts with this code.",
						"Finish", JOptionPane.INFORMATION_MESSAGE);
				clickCount = 0;
			} else if (event.getSource() == button2) {
				if (huffman == null) {
					JOptionPane.showMessageDialog(Gui.this,
							"Please first enter some text and then follow the steps to build the Huffman Tree.",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String text = huffman.getOutputText();
					if (text.equals("") || (text == null)) {
						JOptionPane.showMessageDialog(Gui.this,
								"Please first enter some text and then follow the steps to build the Huffman Tree.",
								"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						outputMessageArea1.setText(text);
						codeDisplay.getFunction("encode");
					}
				}
			} else if (event.getSource() == button3) {
				String text = "";
				text = inputMessageArea2.getText();
				if (text.equals("")) {
					JOptionPane.showMessageDialog(Gui.this, "Please enter some correct text.", "Error",
							JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
				huffman.decode(text);
				codeDisplay.getFunction("decode");
				outputMessageArea2.setText(huffman.getDecodedText());
			}
		}
	}
}