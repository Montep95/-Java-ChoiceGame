import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IdolWorldCup {
    // 매개변수 설정
    static JPanel panelNorth;
    static JPanel panelCenter;
    static JLabel labelMessage;
    static JLabel labelVs; // 이미지 사이 텍스트 매개변수
    static JButton buttonRight;
    static JButton buttonLeft;

    // 이미지 매개변수
    static String[] images = { // 이미지 배열 선언
            "player01.PNG","player02.PNG","player03.PNG",
            "player04.PNG","player05.PNG","player06.PNG",
            "player07.PNG","player08.PNG","player09.PNG",
            "player10.PNG","player11.PNG","player12.PNG",
            "player13.PNG","player14.PNG","player15.PNG",
            "player16.PNG"
    };

    static int imageIndex = 2; // 3번째 이미지를 가리킴
                                // (처음 두장은 초기화면에서 표현이 되기때문)

    // function : image to put on Button Control (레이블은 클릭이 안되기때문)
    static ImageIcon changeImage(String filename){ // filename을 매개변수로  선언
        ImageIcon icon = new ImageIcon("D:\\Develop\\Java_Game\\ChoiceWorldcup\\images\\"+filename); // 현재 프로젝트 폴더 안 경로를 표시 후 filename으로 찾아가는 방식
        Image originImage = icon.getImage();
        Image changedImage = originImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        // 이미지를 변화시켜줌 = 200x200 사이즈의 이미지를 부드럽게 만들어줌
        // 이미지 스케일링 과정

        ImageIcon icon_new = new ImageIcon(changedImage);
        return icon_new;
    }


   static class MyFrame extends JFrame implements ActionListener{
        public MyFrame(String title){
            super(title); // JFrame에 title을 넘겨주는 의미

            // UI Init(UI 초기화)
            this.setSize(450, 250);
            this.setVisible(true); // 윈도우에서 보이게끔 해야함
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 이 버튼이 있어야 창을 닫을 수 있음

            panelNorth = new JPanel();
            labelMessage = new JLabel("Find Your Idol!");
            labelMessage.setFont(new Font("Arial", Font.BOLD, 20));
            panelNorth.add(labelMessage); // 패널의 위쪽에 지금까지 만들어진 메시지를 추가
            this.add("North", panelNorth); // JPanel에 만들어진 panelNorth를 추가해줌

            panelCenter = new JPanel();
            labelVs = new JLabel("vs");
            labelVs.setFont(new Font("Arial", Font.BOLD, 20));
            buttonLeft =  new JButton("LeftButton");
            buttonLeft.setIcon(changeImage("player01.PNG"));
            buttonLeft.setPreferredSize(new Dimension(200, 200));
            // setSize로 하면 제대로 적용되지 않으므로 setPreferredSize 로 만듦
            buttonRight =  new JButton("RightButton");
            buttonRight.setIcon(changeImage("player02.PNG"));
            buttonRight.setPreferredSize(new Dimension(200, 200));

            // Add Click Event
            buttonLeft.addActionListener(this);
            buttonRight.addActionListener(this);
            panelCenter.add(buttonLeft);
            panelCenter.add(labelVs);
            panelCenter.add(buttonRight);
            this.add("Center", panelCenter); // JPanel에 panelCenter를 추가해줌

            this.pack(); // 빈공간을 없애 최적하시켜줌 : Clear empty Space => pack!
        }

       @Override
       // 액션리스너 =  버튼클릭이벤트를 구현하기 위함
       public void actionPerformed(ActionEvent e) {
           // 인덱스가 16개가 넘어가게되면 오류가 발생. 예외처리 필요
           if ( imageIndex == 16) {
               // Last Idol : print
               labelMessage.setText("Found Your Idol!!!");

               // Show One Image : 최종 선정된 이미지만을 남겨야함
               if (e.getActionCommand().equals("LeftButton")) {
                   buttonRight.hide(); // 왼쪽버튼이 최종적으로 눌렸으면 오른쪽버튼 가리기
                   labelVs.hide(); //vs 문구또한 마찬가지
               } else {
                   buttonLeft.hide();
                   labelVs.hide();
               }

           }else{
               if( e.getActionCommand().equals("LeftButton")) {
                   // LeftButton문구가 String 문자열로 전달됨
                   // LeftButton Clicked
                   buttonRight.setIcon(changeImage(images[imageIndex]));
                   imageIndex++;
                   // 왼쪽버튼이 눌렸으므로 오른쪽그림이 바뀌는 구조

               } else{
                   //RightButton Clicked
                   buttonLeft.setIcon(changeImage(images[imageIndex]));
                   imageIndex++;
               }
           }


       }
   }

    public static void main(String[] args){
        new MyFrame("Football Player WorldCup");
    }
}
