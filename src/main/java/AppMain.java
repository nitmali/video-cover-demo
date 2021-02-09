import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class AppMain {
    public static void main(String[] args) {
        try {
            getCover("src/main/resources/8_15488489004165.mp4", "./");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 获取本地视频封面
     * @param videoPath 视频path
     * @param savePath 封面保存path
     */
    public static void getCover(String videoPath, String savePath) throws Exception {
        FFmpegFrameGrabber fFmpegFrameGrabber = FFmpegFrameGrabber.createDefault(new File(videoPath));
        fFmpegFrameGrabber.start();
        int length = fFmpegFrameGrabber.getLengthInFrames();
        int i = 0;
        Frame frame = null;
        while (i < length) {
            frame = fFmpegFrameGrabber.grabFrame();
            // 为了防止出现黑屏的封面图，从第6帧开始取
            if ((i > 6) && (frame.image != null)) {
                break;
            }
            i++;
        }
        if (frame == null) {
            return;
        }
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        File file = new File(savePath + "cover.png");
        ImageIO.write(bufferedImage, "png", file);
        fFmpegFrameGrabber.stop();
    }

    /**
     * 获取网络视频封面
     * @param videoPath 网络视频path
     * @param savePath 封面保存path
     */
    public static void getCoverByNetwork(String videoPath, String savePath) throws Exception {
        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(videoPath);
        // 下面都是一样的
        fFmpegFrameGrabber.start();
        int length = fFmpegFrameGrabber.getLengthInFrames();
        int i = 0;
        Frame frame = null;
        while (i < length) {
            frame = fFmpegFrameGrabber.grabFrame();
            // 为了防止出现黑屏的封面图，从第6帧开始取
            if ((i > 6) && (frame.image != null)) {
                break;
            }
            i++;
        }
        if (frame == null) {
            return;
        }
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        File file = new File(savePath + "cover.png");
        ImageIO.write(bufferedImage, "png", file);
        fFmpegFrameGrabber.stop();
    }
}
