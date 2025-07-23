/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Point;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.FileUpload;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.opencv.core.Point;

/**
 *
 * @author Jason
 */


public class Test extends ListenerAdapter{

    static int select =7;//1 monkey 2 ghost 3 fox 4 Balrog 5 snow 6 room 7 blackcat 8 time
     private static JDA jda;
    private static final String channelId  = "1396656703277367326";
    static {
        //System.load("D:\\opencv\\4-12\\opencv\\build\\java\\x64\\opencv_java4120.dll");
        System.load(System.getProperty("user.dir") + "\\x64\\opencv_java4120.dll");
    }

    
   static class SymbolMatch {
        String symbol;
        Point point;

        SymbolMatch(String symbol, Point point) {
            this.symbol = symbol;
            this.point = point;
        }
    }    
    
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        // 確認訊息來自文字頻道
        if (event.isFromType(ChannelType.TEXT)) {
            // 取得頻道，並轉型成TextChannel
            TextChannel channel = (TextChannel) event.getChannel();

            if (channel.getId().equals(channelId)) {
                String msg = event.getMessage().getContentRaw();
                if (msg.equalsIgnoreCase("hello")) {
                    channel.sendMessage("嗨！你說了 hello").queue();
                }
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        
        
        String token = "test"; // 測試用 token（⚠️ 實際請換成你的真實 Token）

    //String channelId = "884774098234916864"; // 目標頻道 ID



         jda = JDABuilder.createDefault(token,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.MESSAGE_CONTENT
        ).build();

        jda.awaitReady();


        
 
        Random rand = new Random();
        int monkeycount=0;
        
        Robot robot = new Robot();
        
        String templatePath = "test_menu.png";
        String templatePath2 = "test_channel.png";
        String templatePath3 = "test_world.png";
        String templatePath4 = "test_ok.png";
        String templatePath5 = "test_login.png";
        String templatePath6 = "test_choose.png";
        String templatePath_cross = "test_cross.png";
        String templatePath_entergame = "test_entergame.png";
        
        
        String templatePath7 = "test_channel_found_image.png";
        
        String templatePath_monkey1 = "test_monkey.png";
        String templatePath_monkey2 = "test_monkey2.png";
        String templatePath_monkey3 = "test_monkey_tell.png";
        
        String templatePath_ghost = "test_ghost_tell.png";
        
        String templatePath_fox = "test_fox_tell.png";
        String templatePath_fox_blk = "test_fox_tell_blk.png";
        
        String templatePath_barlog="test_Balrog_tell.png";
        
        String templatePath_snow="test_snow_tell.png";
        String templatePath_room="test_room_tell.png";
        String templatePath_cat="test_cat_tell.png";
        String templatePath_cat2="test_cat_tell2.png";
        String templatePath_cat_right="test_cat_right.png";
        String templatePath_cat_start="test_cat_start.png";
        
        String templatePath_time="test_time_tell.png";
        
        

         int i=1;


         

        
        
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                startHPMonitoring();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        
         System.out.println("going...");




         while(i>1000000){
             boolean test=detectCharacterOnScreen(templatePath);
             System.out.println("------1");
             i++;
             Thread.sleep(500000);
         }
         
         
         
         
  Thread.sleep(2000);
    while (true) {
         Point foundPoint= detectCharacterOnScreen2(templatePath);
        if(i==1){
            
            foundPoint = detectCharacterOnScreen2(templatePath);
            i++;
        }else if(i==2){
            foundPoint = detectCharacterOnScreen2(templatePath2);
            i++;
        }else if(i==3){ 
            foundPoint = detectCharacterOnScreen2(templatePath3);
            i++;
        }else if(i==4){    
            foundPoint = detectCharacterOnScreen2(templatePath4);
            i++;
        }else if(i==5){    
            foundPoint = detectCharacterOnScreen2(templatePath5);
            i++;
        }else if(i==6){    
            foundPoint = detectCharacterOnScreen2(templatePath_entergame);
            i++;
        }else if(i==7){    
            foundPoint = detectCharacterOnScreen2(templatePath_cross);
            i++;
        }else if(i==8){    
            foundPoint = detectCharacterOnScreen2(templatePath6);
            i=1;
        }           
    
        if (foundPoint != null) {
            
            

            
            
           // System.out.println("found at: " + foundPoint);

            // 取得圖片大小 (你要改成傳回來或另外讀取)
            Mat template = Imgcodecs.imread(templatePath);
            int clickX = (int)(foundPoint.x + template.cols() / 2);
            
            if(i==4)clickX=clickX-30;
            if(i==8)clickX=clickX-20;
            
            int clickY = (int)(foundPoint.y + template.rows() / 2);
            
             if(i==6||i==7) Thread.sleep(1000 + rand.nextInt(500));
             if(i==8) Thread.sleep(1000 + rand.nextInt(500));
             
             //randomize
             if(i!=4&&i!=8)clickX=clickX+10- rand.nextInt(20);
             if(i!=4&&i!=8)clickY=clickY+10- rand.nextInt(20);
             
            // 移動滑鼠並點擊
            robot.mouseMove(clickX, clickY);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            
            if(i==1&&select==7){
                //System.out.println("k");
                Thread.sleep(1500);
                //System.out.println("startdetecting");
                for(int t=0;t<40;t++){
                    Thread.sleep(20);
                    if(detectCharacterOnScreen(templatePath_cat_start)){
                     t=100;
                   // System.out.println("detected!!!");
                        for(int c=0 ;c<2;c++){                
                            Thread.sleep(1000);

                            // 按住 ↓ 鍵
                            robot.keyPress(KeyEvent.VK_DOWN);
                            //System.out.println("Pressed DOWN");

                            // 等 1 秒
                            Thread.sleep(100 + rand.nextInt(50));

                            // 按一下 Alt 鍵
                            robot.keyPress(KeyEvent.VK_ALT);

                            Thread.sleep(100 + rand.nextInt(80));
                            // 放開 ↓ 鍵
                            robot.keyRelease(KeyEvent.VK_DOWN);
                            robot.keyRelease(KeyEvent.VK_ALT);


                            //System.out.println("Released DOWN");
                        }

                          boolean detect =true;
                                int counter=0;
                            while(detect){
                                Thread.sleep(80);
                                counter++;
                                robot.keyPress(KeyEvent.VK_RIGHT);
                               // System.out.println(counter);
                                if(counter>20){
                                     robot.keyRelease(KeyEvent.VK_RIGHT);
                                     System.out.println("stuck too long reset");
                                    detect=false;
                                }
                                if(detectCharacterOnScreen(templatePath_cat_right)){
                                    robot.keyRelease(KeyEvent.VK_RIGHT);
                                    detect=false;

                                           
                
                                }
                                
                                if(detectCharacterOnScreen(templatePath_cat)||detectCharacterOnScreen(templatePath_cat2)){
                                        robot.keyRelease(KeyEvent.VK_RIGHT);
                                        detect=false;
                                        
                                    robot.keyPress(KeyEvent.VK_DOWN);
                                    //System.out.println("Pressed DOWN");

                                    // 等 1 秒
                                    Thread.sleep(100 + rand.nextInt(50));

                                    // 按一下 Alt 鍵
                                    robot.keyPress(KeyEvent.VK_ALT);

                                    Thread.sleep(100 + rand.nextInt(80));
                                    // 放開 ↓ 鍵
                                    robot.keyRelease(KeyEvent.VK_DOWN);
                                    robot.keyRelease(KeyEvent.VK_ALT);   
                                        
                                        Point found=null;
                                    
                                         Thread.sleep(300);
                                         Point foundPoint_checkchannel= detectCharacterOnScreen2(templatePath);
                                         for(int k=0;k<2;k++){
                                             if(k==0){
                                                 foundPoint_checkchannel= detectCharacterOnScreen2(templatePath);


                                                 if (foundPoint_checkchannel != null){
                                                         Mat template_cat = Imgcodecs.imread(templatePath);
                                                        int clickX_cat = (int)(foundPoint_checkchannel.x + template_cat.cols() / 2);

                                                        int clickY_cat = (int)(foundPoint_checkchannel.y + template_cat.rows() / 2);

                                                        // 移動滑鼠並點擊
                                                        robot.mouseMove(clickX_cat+10- rand.nextInt(20), clickY_cat+10- rand.nextInt(20));
                                                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                                                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);   
                                                             Thread.sleep(100 + rand.nextInt(250));    
                                                  }

                                             }else{
                                                  foundPoint_checkchannel= detectCharacterOnScreen2(templatePath2);

                                                  if (foundPoint_checkchannel != null){
                                                         Mat template_cat = Imgcodecs.imread(templatePath);
                                                        int clickX_cat = (int)(foundPoint_checkchannel.x + template_cat.cols() / 2);

                                                        int clickY_cat = (int)(foundPoint_checkchannel.y + template_cat.rows() / 2);

                                                        // 移動滑鼠並點擊
                                                        robot.mouseMove(clickX_cat+10- rand.nextInt(20), clickY_cat+10- rand.nextInt(20));
                                                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                                                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);   
                                                         Thread.sleep(1500);                             
                                                         found = detectCharacterOnScreen2(templatePath7);
                                                  }
                                             }

                                         }

                                         captureAndSendRegion(found, templatePath_monkey3);
                                         monkeycount++;
                                         System.out.println("-----------------------");
                                         System.out.println("monkeycount: "+monkeycount);
                                         System.out.println("monkey found!!!!!!!!!!");
                                         System.out.println("discord bot reply");
                                         System.out.println("monkeycount:"+monkeycount);
                                         System.out.println("monkey found!!!!!!!!!!");
                                         System.out.println("discord bot reply");
                                         System.out.println("monkeycount: "+monkeycount);
                                         System.out.println("-----------------------");


                                                         


                                    
                                    
                                    
                                    
                                    break;
                                }
                            }


                    }
                }    
                
            }            
            
            
            
            //System.out.println("Mouse clicked at: (" + clickX + "," + clickY + ")");
        } else {
            //System.out.println("not found");
        }
         int sleepTime = 100 + rand.nextInt(100);
        if(select==7) sleepTime = 100 + rand.nextInt(50);
        
        Point found=null;
        if(i==1&&foundPoint != null){
            for(int j=1;j<50;j++){
                
                found = detectCharacterOnScreen2(templatePath_monkey3);
                if(select==2)found = detectCharacterOnScreen2(templatePath_ghost);
               if(select==3)found = detectCharacterOnScreen2(templatePath_fox);
               Thread.sleep(15);
                if(select==3&&found==null)found = detectCharacterOnScreen2(templatePath_fox_blk);
                
                if(select==4)found = detectCharacterOnScreen2(templatePath_barlog);
                if(select==5) found =detectCharacterOnScreen2(templatePath_snow);
                //templatePath_room
                if(select==6) found =detectCharacterOnScreen2(templatePath_room);
                if(select==7) {
                    j+=25;
                    found =detectCharacterOnScreen2(templatePath_cat);
                }
                if(select==8) {
                    found =detectCharacterOnScreen2(templatePath_time);
                }
                Thread.sleep(10);
                if (found!=null) {
                    Thread.sleep(1500);
                    Point foundPoint_checkchannel= detectCharacterOnScreen2(templatePath);
                    for(int k=0;k<2;k++){
                        if(k==0){
                            foundPoint_checkchannel= detectCharacterOnScreen2(templatePath);
                            
                            
                            if (foundPoint_checkchannel != null){
                                    Mat template = Imgcodecs.imread(templatePath);
                                   int clickX = (int)(foundPoint_checkchannel.x + template.cols() / 2);

                                   int clickY = (int)(foundPoint_checkchannel.y + template.rows() / 2);

                                   // 移動滑鼠並點擊
                                   robot.mouseMove(clickX, clickY);
                                   robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                                   robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);   
                                        Thread.sleep(200 + rand.nextInt(250));    
                             }
                            
                        }else{
                             foundPoint_checkchannel= detectCharacterOnScreen2(templatePath2);
                             
                             if (foundPoint_checkchannel != null){
                                    Mat template = Imgcodecs.imread(templatePath);
                                   int clickX = (int)(foundPoint_checkchannel.x + template.cols() / 2);

                                   int clickY = (int)(foundPoint_checkchannel.y + template.rows() / 2);
                                   
                                   // 移動滑鼠並點擊
                                   robot.mouseMove(clickX, clickY);
                                   robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                                   robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);   
                                    Thread.sleep(1500 + rand.nextInt(250));                             
                                    found = detectCharacterOnScreen2(templatePath7);
                             }
                        }
                        
                    }
                    
                    captureAndSendRegion(found, templatePath_monkey3);
                    monkeycount++;
                    System.out.println("-----------------------");
                    System.out.println("monkeycount: "+monkeycount);
                    System.out.println("monkey found!!!!!!!!!!");
                    System.out.println("discord bot reply");
                    System.out.println("monkeycount:"+monkeycount);
                    System.out.println("monkey found!!!!!!!!!!");
                    System.out.println("discord bot reply");
                    System.out.println("monkeycount: "+monkeycount);
                    System.out.println("-----------------------");
                    
                    
                    j=1000;
                }
            }

            
        }
         Thread.sleep(sleepTime); 
        if(i==8)robot.mouseMove(rand.nextInt(1250), rand.nextInt(50));
    }        
        
  
        
        
        /**
        while (true) {
            boolean found = detectCharacterOnScreen(templatePath);
            if (found) {
                System.out.println("found");
            } else {
               boolean found2 = detectCharacterOnScreen(templatePath2); 
              if (found2) {
                System.out.println("found");
              } else {
                System.out.println("not found");
              }
            }

            // 每 2 秒執行一次
            Thread.sleep(200);
        }**/
        
    }

    public static boolean detectCharacterOnScreen(String templatePath) throws Exception {
        
 
    File file = new File(templatePath);
    if (!file.exists()) {
        System.err.println("找不到檔案：" + templatePath);
        return false;
    }        
        
        
        // 擷取螢幕截圖
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenImage = new Robot().createScreenCapture(screenRect);

        // 轉成 OpenCV Mat
        Mat screenMat = bufferedImageToMat(screenImage);
        Mat template = Imgcodecs.imread(templatePath);

        if (template.empty() || screenMat.empty()) {
            System.err.println("讀取圖片失敗");
            return false;
        }

        // 模板比對
        int resultCols = screenMat.cols() - template.cols() + 1;
        int resultRows = screenMat.rows() - template.rows() + 1;
        Mat result = new Mat(resultRows, resultCols, CvType.CV_32FC1);

        Imgproc.matchTemplate(screenMat, template, result, Imgproc.TM_CCOEFF_NORMED);
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);

        // 設定匹配門檻值
        double threshold = 0.85;
        return mmr.maxVal >= threshold;
    }
    
    public static Point detectCharacterOnScreen2(String templatePath) throws Exception {
    File file = new File(templatePath);
    if (!file.exists()) {
        System.err.println("找不到檔案：" + templatePath);
        return null;
    }        

    Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    BufferedImage screenImage = new Robot().createScreenCapture(screenRect);

    Mat screenMat = bufferedImageToMat(screenImage);
    Mat template = Imgcodecs.imread(templatePath);

    if (template.empty() || screenMat.empty()) {
        System.err.println("讀取圖片失敗");
        return null;
    }

    int resultCols = screenMat.cols() - template.cols() + 1;
    int resultRows = screenMat.rows() - template.rows() + 1;
    Mat result = new Mat(resultRows, resultCols, CvType.CV_32FC1);

    Imgproc.matchTemplate(screenMat, template, result, Imgproc.TM_CCOEFF_NORMED);
    Core.MinMaxLocResult mmr = Core.minMaxLoc(result);

    double threshold = 0.85;
    if (mmr.maxVal >= threshold) {
        return mmr.maxLoc;
    } else {
        return null;
    }
}
    
    public static void captureAndSendRegion(Point matchLoc, String originalTemplatePath) throws Exception {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        
        if(matchLoc==null)return;
        
        // 擴張區域 +/- 100 像素
        int x = (int) matchLoc.x;
        int y = (int) matchLoc.y;

        int startX = Math.max(0, x - 200);
        int startY = Math.max(0, y - 100);

        int endX = Math.min(screenRect.width, x + 200);
        int endY = Math.min(screenRect.height, y + 100);

        int width = endX - startX;
        int height = endY - startY;

        Robot robot = new Robot();
        BufferedImage subImg = robot.createScreenCapture(new Rectangle(startX, startY, width, height));

        // 把擷取的圖片存成暫存檔
        File outFile = new File("capture_region.png");
        ImageIO.write(subImg, "png", outFile);

        sendImageToDiscord(outFile);
    }    

  
     public static void sendImageToDiscord(File file) {
        TextChannel channel = jda.getTextChannelById(channelId);
        if (channel == null) {
            System.err.println("找不到 Discord 頻道: " + channelId);
            return;
        }

        if (!file.exists()) {
            System.err.println("找不到圖片檔案: " + file.getAbsolutePath());
            return;
        }
         if(select ==1)//1 monkey 2 ghost
            channel.sendMessage("殭屍猴王").addFiles(FileUpload.fromData(file)).queue();
          if(select ==2)//1 monkey 2 ghost
              channel.sendMessage("書靈").addFiles(FileUpload.fromData(file)).queue();
          if(select ==3)//1 monkey 2 ghost
              channel.sendMessage("九尾狐").addFiles(FileUpload.fromData(file)).queue();
         if(select ==4)//1 monkey 2 ghost
              channel.sendMessage("巴洛古").addFiles(FileUpload.fromData(file)).queue();  
           if(select ==5)//1 monkey 2 ghost
              channel.sendMessage("雪毛").addFiles(FileUpload.fromData(file)).queue();  
           if(select==6)
               channel.sendMessage("姑姑王").addFiles(FileUpload.fromData(file)).queue();  
           if(select==7)
               channel.sendMessage("黑貓").addFiles(FileUpload.fromData(file)).queue();      
           if(select==8)
               channel.sendMessage("姑姑鐘").addFiles(FileUpload.fromData(file)).queue();  
    }
    
     
     // ➤ 這裡你換成從 template 匹配得到的完整字串
    private static String getCurrentHPString() {
        // TODO: 用你的 OpenCV 數字辨識結果代替
        return "5820/8092";  // 測試用
    }

    // ➤ 從 "5820/8092" 抓出左邊的數字
    private static int parseLeftNumber(String str) {
        if (!str.contains("/")) return 0;
        try {
            return Integer.parseInt(str.split("/")[0]);
        } catch (Exception e) {
            return 0;
        }
    }    
    
    public static Mat bufferedImageToMat(BufferedImage bi) {
    // 將影像轉成 OpenCV 支援的 TYPE_3BYTE_BGR 格式
    BufferedImage convertedImg = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
    convertedImg.getGraphics().drawImage(bi, 0, 0, null);

    byte[] pixels = ((DataBufferByte) convertedImg.getRaster().getDataBuffer()).getData();
    Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
    mat.put(0, 0, pixels);
    return mat;
    }
    
    // 模板比對工具方法
    private static List<SymbolMatch> matchSymbols(Mat sourceGray, Mat templateGray, String symbol, double threshold) {
        List<SymbolMatch> results = new ArrayList<>();
        Mat result = new Mat();
        Imgproc.matchTemplate(sourceGray, templateGray, result, Imgproc.TM_CCOEFF_NORMED);

        for (int y = 0; y < result.rows(); y++) {
            for (int x = 0; x < result.cols(); x++) {
                double match = result.get(y, x)[0];
                if (match >= threshold) {
                    results.add(new SymbolMatch(symbol, new Point(x, y)));
                }
            }
        }
        
        return filterNonOverlapping(results, templateGray.width());
    }

    // 避免重複點（非極大值抑制簡化版）
    private static List<SymbolMatch> filterNonOverlapping(List<SymbolMatch> list, int minDistance) {
        List<SymbolMatch> filtered = new ArrayList<>();
        for (SymbolMatch a : list) {
            boolean isNear = false;
            for (SymbolMatch b : filtered) {
                if (Math.abs(a.point.x - b.point.x) < minDistance / 2.0) {
                    isNear = true;
                    break;
                }
            }
            if (!isNear) {
                filtered.add(a);
            }
        }
        return filtered;
    }
    


    private static void startHPMonitoring() throws Exception {
        int counter=0;
        Robot robot = new Robot();

        // 初始化模板
        Mat hpTemplate = Imgcodecs.imread("number\\hptest.png", Imgcodecs.IMREAD_GRAYSCALE);
        if (hpTemplate.empty()) {
            System.out.println("❌ hp 模板讀取失敗");
            return;
        }

        // 預先讀入所有數字模板
        String[] symbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "dash"};
        Map<String, Mat> templates = new HashMap<>();
        for (String sym : symbols) {
            Mat tmp = Imgcodecs.imread("number\\" + sym + ".png", Imgcodecs.IMREAD_GRAYSCALE);
            if (tmp.empty()) {
                System.out.println("❌ 模板讀取失敗: " + sym);
                continue;
            }
            templates.put(sym, tmp);
        }

        long count = 0;
        while (true) {
           // count++;

            // 擷取螢幕
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenCapture = robot.createScreenCapture(screenRect);
            Mat screenMat = bufferedImageToMat(screenCapture);

            // 灰階處理
            Mat screenGray = new Mat();
            Imgproc.cvtColor(screenMat, screenGray, Imgproc.COLOR_BGR2GRAY);

            // 匹配 HP 模板位置
            Mat result2 = new Mat();
            Imgproc.matchTemplate(screenGray, hpTemplate, result2, Imgproc.TM_CCOEFF_NORMED);
            Point hpTopLeft = Core.minMaxLoc(result2).maxLoc;

            // 定義 ROI
            int roiX = (int) hpTopLeft.x + 10;
            int roiY = (int) hpTopLeft.y;
            int roiWidth = 200;
            int roiHeight = hpTemplate.rows();

            if (roiX + roiWidth > screenGray.cols() || roiY + roiHeight > screenGray.rows()) {
                System.out.println("❌ ROI 超出範圍");
                continue;
            }

            Mat numberRegion = new Mat(screenGray, new Rect(roiX, roiY, roiWidth, roiHeight));

            // 存檔 debug 用
           // Imgcodecs.imwrite("C:\\Users\\Jason\\Documents\\number\\extracted_numbers.png", numberRegion);

            // 模板比對
            List<SymbolMatch> allMatches = new ArrayList<>();
            for (Map.Entry<String, Mat> entry : templates.entrySet()) {
                String sym = entry.getKey();
                Mat tmpl = entry.getValue();

                if (tmpl.cols() > numberRegion.cols() || tmpl.rows() > numberRegion.rows()) {
                    continue;
                }

                Mat result = new Mat();
                Imgproc.matchTemplate(numberRegion, tmpl, result, Imgproc.TM_CCOEFF_NORMED);

                for (int y = 0; y < result.rows(); y++) {
                    for (int x = 0; x < result.cols(); x++) {
                        if (result.get(y, x)[0] >= 0.9) {
                            allMatches.add(new SymbolMatch(sym, new Point(x, y)));
                        }
                    }
                }
            }

            // 左到右排序
            allMatches.sort(Comparator.comparingDouble(m -> m.point.x));
            StringBuilder sb = new StringBuilder();
            for (SymbolMatch match : allMatches) {
                if (match.symbol.equals("dash")) sb.append("/");
                else sb.append(match.symbol);
            }

            String resultStr = sb.toString();
            int currentHP = parseLeftNumber(resultStr);
            //System.out.println("current: " + currentHP);

            if (currentHP < 6000 && currentHP != 0) {
                counter++;
                System.out.println("hp too low INSERT :"+counter);
                robot.keyPress(KeyEvent.VK_INSERT);
                robot.keyRelease(KeyEvent.VK_INSERT);
            } else {
               // System.out.println("hp ok");
            }

            Thread.sleep(300); // 每次輪詢間隔
        }
    }


    
    
}
