package view;

import model.checkFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author 痞老板
 * @Project: checkFile
 * @Package:view
 * @date 2018/5/20 11:39
 * @description
 **/
@SuppressWarnings("serial")
public class FileChooser extends JFrame implements ActionListener {
    JButton open=null;
    public FileChooser(){
        open=new JButton("点击发射");
        this.setTitle("痞老板的坦克车");
        this.add(open);
        setSize(800,200);
        this.setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        open.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        try {
            JFileChooser jfc=new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
            jfc.showDialog(new JLabel(), "选择");
            File file=jfc.getSelectedFile();
            if (file==null){
                return;
            }
            if(file.isDirectory()){
                JOptionPane.showMessageDialog(null, "请选择文件而不是文件夹");
            }else if(file.isFile()){
                try {
                    checkFile checkFile=new checkFile();
                    boolean b=checkFile.checkfile(file);
                    if (!b){
                        JOptionPane.showMessageDialog(null, "处理失败");
                    }
                }catch (FileNotFoundException e2){
                    JOptionPane.showMessageDialog(null, "H5文件中的其他文件路径错了");
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (HeadlessException e1) {
            e1.printStackTrace();
        }

    }



}