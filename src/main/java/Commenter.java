import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.List;

public class Commenter {
    public void addCommentToJavaFiles(String path, String comment) {
        File file = new File(path);
        if (file.isDirectory())
            addCommentToJavaFiles(file.getPath(), comment);
        if (file.isFile()) ;
    }

    public void FindCertainExtension(String folder, String ext) throws FileNotFoundException {
        File dir = new File(folder);
        GenericExtFilter extFilter = new GenericExtFilter(".java");
        File[] arrOfFiles = dir.listFiles(extFilter);
        for (File file : arrOfFiles) {
            System.out.println(file);
        }

//            if(dir.isDirectory()==false){
//
//                System.out.println("Directory does not exists : " + folder);
//                return;
//            }
//
//            // list out all the file name and filter by the extension
//
//            FileInputStream in = new FileInputStream(dir);
//            String[] list = dir.list();
//            StringBuilder s = new StringBuilder();
//
//
//            if (list.length == 0) {
//                System.out.println("no files end with : " + ext);
//                return;
//            }
//
//            for (String file : list) {
//                String temp = new StringBuffer(folder).append(File.separator)
//                        .append(file).toString();
//                System.out.println("file : " + temp);
//            }
    }

    // inner class, generic extension filter
    public class GenericExtFilter implements FilenameFilter {

        private String ext;

        public GenericExtFilter(String ext) {
            this.ext = ext;
        }

        public boolean accept(File dir, String name) {
            return (name.endsWith(ext));
        }
    }
}
