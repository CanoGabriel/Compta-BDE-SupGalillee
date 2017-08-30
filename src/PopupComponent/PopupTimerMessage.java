package PopupComponent;


import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class PopupTimerMessage extends JDialog {

	private static final long	serialVersionUID	= -5415492844523385406L;
	private JLabel				messageLabel;
	private long				timeout;

	public PopupTimerMessage (	Frame owner,
								String title,
								String message,
								long timeout) {

		super(owner, title);
		messageLabel = new JLabel(message);
		this.timeout = timeout;
		this.setUndecorated(true);
		add(messageLabel);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(owner);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowOpened (WindowEvent e) {

				// don't mess with the EDT, vilain
				new Thread() {

					@Override
					public void run () {

						try {
							Thread.sleep(PopupTimerMessage.this.timeout);
						}
						catch (InterruptedException ex) {
							ex.printStackTrace();
						}
						finally {
							dispose();
						}
					}
				}.start();
			}
		});
	}

	public static void showAutoCloseDialog (Frame owner, String title, String message, long timeout) {

		PopupTimerMessage dialog = new PopupTimerMessage(owner, title, message, timeout);
		dialog.setVisible(true);
	}
}
