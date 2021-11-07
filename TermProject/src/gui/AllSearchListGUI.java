package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cafe.Cafe;
import cafe.Dessert;
import cafe.Drink;
import cafe.Menu;

public class AllSearchListGUI extends JPanel {
	static Cafe cafe = new Cafe();

	int page = 0;
	int[] locationX = { 20, 250, 20, 250, 20, 250 };
	int[] locationY = { 70, 70, 270, 270, 470, 470 };
	String name;
	
	public AllSearchListGUI(String name) {
		this.name = name;
		StartGUI.cafe.searchDrinkIdx.clear();
		StartGUI.cafe.searchDessertIdx.clear();
		StartGUI.cafe.basicSearchDrink(name);
		StartGUI.cafe.basicSearchDessert(name);
		paintGUI();
	}

	public void paintGUI() {
		setLayout(null);
		setSize(500, 800);
		setLocation(0, 0);
		setBackground(BackGroundFrameGUI.backGroundColor);

		// 상단바
		JLabel topBar = new JLabel("검색된 메뉴");
		topBar.setBounds(0, 0, 500, 50);
		topBar.setHorizontalAlignment(JLabel.CENTER);
		topBar.setBackground(BackGroundFrameGUI.topBarColor);
		topBar.setOpaque(true);
		topBar.setFont(new Font("고딕", Font.BOLD, 20));
		add(topBar);

		JButton moveMainButton = new JButton("메인 이동");
		moveMainButton.setBounds(20, 660, 90, 30);
		moveMainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				revalidate();
				repaint();
				UserMainGUI move = new UserMainGUI();
				add(move);
			}
		});
		add(moveMainButton);
		
		MenuGUI[] menuItems = new MenuGUI[6]; // null로 초기화 후
		for (int i = 0; i < 6; i++) {
			int indexPage = page * 6 + i;
			if (StartGUI.cafe.searchDrinkIdx.size() == 0 && StartGUI.cafe.searchDessertIdx.size() == 0)
				break;
			if (indexPage < StartGUI.cafe.searchDrinkIdx.size()) {	//음료 메뉴에 대해 출력
				if (!StartGUI.cafe.searchDrinkIdx.isEmpty()) {
					int index = StartGUI.cafe.searchDrinkIdx.get(indexPage);
					Menu menuObject = (Menu) StartGUI.cafe.menudrinkMgr.mList.get(index);
					menuItems[i] = new MenuGUI(menuObject); // 객체 생성
					menuItems[i].setSize(220, 180);
					menuItems[i].setLocation(locationX[i], locationY[i]);
					menuItems[i].detailButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							removeAll();
							revalidate();
							repaint();
							add(new DrinkMenuDetailGUI(cafe, index));
						}
					});
					add(menuItems[i]);
				}
			} else {	//디저트 메뉴에 대해 출력
				if (indexPage - StartGUI.cafe.searchDrinkIdx.size()<StartGUI.cafe.searchDessertIdx.size()) {
					int index = StartGUI.cafe.searchDessertIdx.get(indexPage - StartGUI.cafe.searchDrinkIdx.size());
					Menu menuObject = (Menu) StartGUI.cafe.menudessertMgr.mList.get(index);
					menuItems[i] = new MenuGUI(menuObject); // 객체 생성
					menuItems[i].setSize(220, 180);
					menuItems[i].setLocation(locationX[i], locationY[i]);
					menuItems[i].detailButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							removeAll();
							revalidate();
							repaint();
							add(new DessertMenuDetailGUI(cafe, index));
						}
					});
					add(menuItems[i]);
				}
			}
			
			// 이전 페이지 이동 버튼
			JButton PrevButton = new JButton("<<Prev");
			PrevButton.setBounds(140, 700, 100, 40);
			PrevButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (page > 0) {
						page -= 1;
						removeAll();
						revalidate();
						repaint();
						paintGUI();
					}
				}
			});
			add(PrevButton);

			// 다음 페이지 이동 버튼
			JButton NextButton = new JButton("Next>>");
			NextButton.setBounds(260, 700, 100, 40);
			NextButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((StartGUI.cafe.searchDrinkIdx.size() + StartGUI.cafe.searchDessertIdx.size() - 1) / 6 > page) {
						page += 1;
						removeAll();
						revalidate();
						repaint();
						paintGUI();
					}
				}
			});
			add(NextButton);
		}
		
		// 현재 페이지 번호
		JLabel pageLabel = new JLabel("page : " + (page + 1));
		pageLabel.setBounds(200, 660, 100, 40);
		pageLabel.setHorizontalAlignment(JLabel.CENTER);
		pageLabel.setForeground(Color.WHITE);
		add(pageLabel);

		setVisible(true);
	}

}
