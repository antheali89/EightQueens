import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
public class eightQueens extends JPanel {
   private JLabel[][] board;
   private char[][] matrix;
   private JButton reset;
   private JTextField textField;
   private int num;
   private ImageIcon queenPic;
   private JLabel title;
   
   public eightQueens() {
      setLayout(new BorderLayout());
      
      JPanel north = new JPanel();
      north.setLayout(new FlowLayout());
      add(north, BorderLayout.NORTH);
      textField = new JTextField();
      textField.setColumns(4);
      north.add(textField);
      textField.addActionListener(new createBoard());
      
      queenPic = new ImageIcon("queen.png");
      
      title = new JLabel("How many queens?");
      north.add(title);
      
      reset = new JButton("Reset");
      reset.addActionListener(new Listener());
      JPanel south = new JPanel();
      south.setLayout(new FlowLayout());
      add(south, BorderLayout.SOUTH);
      south.add(reset);
   }
   
   private class createBoard implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         
         num = Integer.parseInt(textField.getText());
         if(num < 4) {
            title.setText("Too small");
            return;
         }
         board = new JLabel[num][num];
         matrix = new char[num][num];
         JPanel center = new JPanel();
         center.setLayout(new GridLayout(num, num));
         add(center, BorderLayout.CENTER);
         for(int r = 0; r < num; r++) {
            for(int c = 0; c < num; c++) {
                board[r][c] = new JLabel();
                
                board[r][c].setBorder(new LineBorder(Color.BLACK));
                board[r][c].setIcon(null);
                center.add(board[r][c]);
                
             }
         }

         //timer = new Timer(500, this);
         //timer.start();
         run(0, 0, num);
      }
   }
   
   private class Listener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         if(e.getSource() == reset) {
            title.setText("How many queens?");
            matrix = null;
            
            for(int r = 0; r < num; r++)
               for(int c = 0; c < num; c++) 
                  board[r][c].setIcon(null);
            
            //textField.setText("hhhhhhhhhhhhhh");
         }
      }
   }
   
   private boolean canPlaceQueen(int row, int col) {
      //board[row][col].setText('Q');
      if(row > matrix.length || row < 0 || col > matrix[0].length || col < 0)
         return false;
      for(int c1 = col - 1; c1 >= 0; c1--) {
         if(matrix[row][c1] == 'Q') {
            return false;
         }
      }
      for(int r2 = row - 1, c2 = col- 1; r2 >= 0 && c2 >= 0; r2--, c2--) {//check downward diagonals
         if(matrix[r2][c2] == 'Q') {
            return false;
         }
      }
      for(int r3 = row + 1, c3 = col - 1; r3 >= 0 && r3 < num && c3 >= 0; r3++, c3--) {//upward diagonal
         if(matrix[r3][c3] == 'Q') {
            return false;
         }
      }
      return true;
   }
   
   private void placeQueen(int row, int col) {
      matrix[row][col] = 'Q';
      board[row][col].setIcon(queenPic);
      board[row][col].setHorizontalAlignment(JLabel.CENTER);
   }
   
   private void removeQueen(int row, int col) {
      matrix[row][col] = ' ';
      board[row][col].setIcon(null);
   }
   
   private boolean run(int row, int col, int queens) {
      //if(row >= matrix.length || col >= matrix[0].length)
         //return false;
      if(queens == 0)
         return true;
      for(int r = row; r < num; r++) {
         if(canPlaceQueen(r, col)) {
            placeQueen(r, col);
            if(run(0, col + 1, queens - 1))
               return true;
         }
            removeQueen(r, col);
            //run(i + 1, col, queens - 1);
      }
      return false;
   }
   
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("8 queens");
      frame.setSize(400, 400);
      frame.setLocation(200, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new eightQueens());
      frame.setVisible(true);
   }
}