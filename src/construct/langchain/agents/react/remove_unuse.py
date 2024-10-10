import re
import os


def find_imports(java_code):
    """
    提取Java代码中的所有导入语句
    """
    import_pattern = re.compile(r'^\s*import\s+([\w\.]+\*?)\s*;', re.MULTILINE)
    return import_pattern.findall(java_code)


def find_references(java_code):
    """
    提取Java代码中的所有类和接口引用
    """
    # 去除import和package语句，简化引用查找
    java_code = re.sub(r'^\s*import\s+[\w\.]+\s*;', '', java_code, flags=re.MULTILINE)
    java_code = re.sub(r'^\s*package\s+[\w\.]+\s*;', '', java_code, flags=re.MULTILINE)

    # 查找所有的标识符
    identifier_pattern = re.compile(r'\b([A-Z][\w]*)\b')
    return set(identifier_pattern.findall(java_code))


def remove_unused_imports(java_code):
    """
    删除Java代码中未使用的导入语句
    """
    flag = 0

    imports = find_imports(java_code)
    references = find_references(java_code)

    used_imports = []
    for imp in imports:
        if '*' in imp:  # 保留所有通配符导入
            used_imports.append(f'import {imp};')
            continue

        class_name = imp.split('.')[-1]
        if class_name in references:     # 判断如果import class是否在java code中出现
            used_imports.append(f'import {imp};')
        else:
            flag = 1

    if flag:
        print('------ Processing remove unuse import code.')
    else:
        return java_code   # 不存在多余的import info 则返回原始代码

    # 构建新的Java代码，保留有用的导入
    new_code_lines = []
    import_section = True
    for line in java_code.splitlines():
        if line.strip().startswith('import '):
            continue
        if import_section and not line.strip():
            # 忽略导入和包声明之间的空行
            continue
        if import_section and line.strip() and not line.strip().startswith('package '):
            import_section = False
            new_code_lines.extend(used_imports)
        new_code_lines.append(line)

    return '\n'.join(new_code_lines)


def process_java_file(file_path):
    """
    处理Java文件，删除未使用的导入语句
    """
    with open(file_path, 'r', encoding='utf-8') as file:
        java_code = file.read()

    new_java_code = remove_unused_imports(java_code)

    with open(file_path, 'w', encoding='utf-8') as file:
        file.write(new_java_code)


if __name__ == "__main__":
    # 指定要处理的Java文件路径
    # java_file_path = 'path/to/YourJavaFile.java'
    # process_java_file(java_file_path)
    # print(f"Processed file: {java_file_path}")

    # 解析 Java 源文件
    source_code = """
    import java.util.List;
    import java.io.File;  // 这个 import 未被使用

    public class Test {
        public static void main(String[] args) {
            List<String> list = new ArrayList<>();
        }
    }
    """

    source_code = """
    import org.apache.commons.imaging.ImageFormats;
    import org.apache.commons.imaging.Imaging;
    import org.apache.commons.imaging.common.ImageBuilder;
    import org.apache.commons.imaging.common.RgbBufferedImageFactory;
    
    import java.awt.*;
    import java.awt.image.BufferedImage;
    import java.io.File;
    import java.io.IOException;
    
    public class DepthOfFieldSimulator {
    
        public static void simulateDepthOfField(String imagePath, String outputPath, int focusStart, int focusEnd) throws IOException, ImageReadException, ImageWriteException {
            // Load the image
            BufferedImage image = Imaging.getBufferedImage(new File(imagePath));
    
            // Create a blurred version of the image
            BufferedImage blurredImage = blurImage(image);
    
            // Create the final image combining focused and blurred parts
            BufferedImage finalImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics g = finalImage.getGraphics();
    
            for (int y = 0; y < image.getHeight(); y++) {
                if (y >= focusStart && y <= focusEnd) {
                    // Draw focused area
                    g.drawImage(image, 0, y, image.getWidth(), y + 1, 0, y, image.getWidth(), y + 1, null);
                } else {
                    // Draw blurred area
                    g.drawImage(blurredImage, 0, y, image.getWidth(), y + 1, 0, y, image.getWidth(), y + 1, null);
                }
            }
            g.dispose();
    
            // Save the final image with the correct ImageFormat
            Imaging.writeImage(finalImage, new File(outputPath), ImageFormats.PNG);
        }
    
        public static BufferedImage blurImage(BufferedImage image) {
            // Simple box blur implementation
            int radius = 5; // Radius of the blur
            BufferedImage blurred = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int y = radius; y < image.getHeight() - radius; y++) {
                for (int x = radius; x < image.getWidth() - radius; x++) {
                    int r = 0, g = 0, b = 0;
                    int count = 0;
                    for (int dy = -radius; dy <= radius; dy++) {
                        for (int dx = -radius; dx <= radius; dx++) {
                            int rgb = image.getRGB(x + dx, y + dy);
                            r += (rgb >> 16) & 0xFF;
                            g += (rgb >> 8) & 0xFF;
                            b += rgb & 0xFF;
                            count++;
                        }
                    }
                    r /= count;
                    g /= count;
                    b /= count;
                    int rgb = (r << 16) | (g << 8) | b;
                    blurred.setRGB(x, y, rgb);
                }
            }
            return blurred;
        }
    
    //     public static void main(String[] args) {
    //         try {
    //             simulateDepthOfField("input.png", "output.png", 100, 300);
    //         } catch (IOException e) {
    //             e.printStackTrace();
    //         }
    //     }
    }
    """

    new_java_code = remove_unused_imports(source_code)
    print(new_java_code)
