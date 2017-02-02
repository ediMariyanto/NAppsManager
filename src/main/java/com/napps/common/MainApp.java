package com.napps.common;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import com.napps.common.view.UIApp;

public class MainApp
{
	private static String iconPath = "D:/Kalacakra/NAppsManager/target/classes/com/napps/common/icoNapps.png";
	private static RandomAccessFile randomFile;

	public final void startApp()
	{
		if (SystemTray.isSupported())
		{
			PopupMenu popup = new PopupMenu();
			MenuItem menuOpen = new MenuItem("Open");
			MenuItem menuClose = new MenuItem("Close");
			popup.add(menuOpen);
			popup.addSeparator();
			popup.add(menuClose);

			menuOpen.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					UIApp main = new UIApp();
					main.setVisible(true);
				}
			});
			menuClose.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					int konfirmasi = JOptionPane.showConfirmDialog(null, "Are you sure close Scheduler? ",
							"Exit Scheduler", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (konfirmasi == JOptionPane.YES_OPTION)
					{
						System.exit(0);
					}
				}
			});

			final SystemTray systemTray = SystemTray.getSystemTray();
			final TrayIcon trayIcon = new TrayIcon(new ImageIcon(iconPath).getImage(), "NAppsManager V 1.0.0", popup);
			trayIcon.setImageAutoSize(true);

			MouseAdapter mouseAdapter = new MouseAdapter()
			{

				@Override
				public void mouseClicked(MouseEvent e)
				{

				}
			};

			trayIcon.addMouseListener(mouseAdapter);
			try
			{
				systemTray.add(trayIcon);
			} catch (Exception e)
			{
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) throws IOException
	{
		MainApp main = new MainApp();

		randomFile = new RandomAccessFile("MainApp.class", "rw");

		FileChannel channel = randomFile.getChannel();

		if (channel.lock() == null)
		{
			JOptionPane.showMessageDialog(null, "Already Running !");
		} else
		{
			main.startApp();
		}
	}
}
