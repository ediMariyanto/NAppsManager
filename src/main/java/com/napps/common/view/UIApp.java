package com.napps.common.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SuppressWarnings("serial")
public class UIApp extends JFrame
{

	private JTextArea textArea;

	private JButton buttonStart = new JButton("Start");
	private JButton buttonStop = new JButton("Stop");

	public UIApp()
	{
		super("NAppsManager Aplication");

		buttonStart.setEnabled(true);
		buttonStop.setEnabled(false);

		textArea = new JTextArea(150, 100);
		textArea.setEditable(false);
		PrintStream printStream = new PrintStream(new OutputTarget(textArea));

		System.setOut(printStream);
		System.setErr(printStream);

		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.anchor = GridBagConstraints.WEST;

		add(buttonStart, constraints);

		constraints.gridx = 2;
		add(buttonStop, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;

		add(new JScrollPane(textArea), constraints);

		buttonStop.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ExecuteApp app = new ExecuteApp();
				app.confirmStopApp();
				buttonStart.setEnabled(true);
				buttonStop.setEnabled(false);
			}
		});

		buttonStart.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				ExecuteApp app = new ExecuteApp();
				app.executeApp();
				AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExecuteApp.class);
				// System.out.println(context);
				buttonStart.setEnabled(false);
				buttonStop.setEnabled(true);

			}

		});
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		setExtendedState(MAXIMIZED_BOTH);

	}

}