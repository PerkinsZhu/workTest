package com.zpj.project.video;

/***
 @author  Perkins Zhu
 @date  2017年4月28日 下午1:51:54
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.PrefetchCompleteEvent;
import javax.media.RealizeCompleteEvent;
import javax.media.Time;
import javax.media.protocol.DataSource;

public class MyVideoPlayer implements ControllerListener {
	public static void main(String[] args) {
		MyVideoPlayer sp = new MyVideoPlayer();
		sp.play();
	}

	private Frame f;
	// private Player videoplayer;
	// private Player audioplayer;
	private Player dualPlayer;
	private Component visual;
	private Component control = null;
	private MediaLocator mediaLocator;
	private MediaLocator audioLocator;

	public void play() {
		f = new Frame("MyPlayer");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				if (dualPlayer != null) {
					dualPlayer.close();
				}
				System.exit(0);
			}
		});
		f.setSize(500, 400);

		f.setVisible(true);

		try {

			mediaLocator = new MediaLocator("vfw://0");// 此类描述媒体目录的地址????
			audioLocator = new MediaLocator("javasound://44100");
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		try {

			DataSource[] dataSources = new DataSource[2];
			dataSources[0] = Manager.createDataSource(mediaLocator);
			dataSources[1] = Manager.createDataSource(audioLocator);
			DataSource ds = Manager.createMergingDataSource(dataSources);
			dualPlayer = Manager.createPlayer(ds);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		dualPlayer.realize();
		dualPlayer.addControllerListener(this);

	}

	private int videoWidth = 0;
	private int videoHeight = 0;
	private int controlHeight = 30;
	private int insetWidth = 10;
	private int insetHeight = 30;

	// 监听player的相关事件
	public void controllerUpdate(ControllerEvent ce) {
		if (ce instanceof RealizeCompleteEvent) {
			// player实例化完成后进行player播放前预处理
			dualPlayer.prefetch();
		} else if (ce instanceof PrefetchCompleteEvent) {
			if (visual != null)
				return;

			// 取得player中的播放视频的组件，并得到视频窗口的大小
			// 然后把视频窗口的组件添加到Frame窗口中，
			if ((visual = dualPlayer.getVisualComponent()) != null) {
				Dimension size = visual.getPreferredSize();
				videoWidth = size.width;
				videoHeight = size.height;
				f.add(visual);
			} else {
				videoWidth = 320;
			}

			// 取得player中的视频播放控制条组件，并把该组件添加到Frame窗口中
			if ((control = dualPlayer.getControlPanelComponent()) != null) {
				controlHeight = control.getPreferredSize().height;
				f.add(control, BorderLayout.SOUTH);
			}

			// 设定Frame窗口的大小，使得满足视频文件的默认大小
			f.setSize(videoWidth + insetWidth, videoHeight + controlHeight + insetHeight);
			f.validate();

			// 启动视频播放组件开始播放
			dualPlayer.start();
		} else if (ce instanceof EndOfMediaEvent) {
			// 当播放视频完成后，把时间进度条恢复到开始，并再次重新开始播放
			dualPlayer.setMediaTime(new Time(0));
			// videoplayer.start();
			// audioplayer.start();
			dualPlayer.start();
		}
	}
}