package real.droid.libx.core;

import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RuntimeX {
    public static final int DEFAULT_ERROR = -0x111;

    public static class Result {
        private final int status;
        private final String msg;

        public Result(int code, String msg) {
            this.status = code;
            this.msg = msg;
        }

        public int getStatus() {
            return status;
        }

        public String getMsg() {
            return msg;
        }
    }

    private RuntimeX() {
    }

    public static RuntimeX on() {
        return new RuntimeX();
    }

    public Result runtimeCommand(String cmd) {
        int status = DEFAULT_ERROR;
        String output = "";
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            CombineOutput combineOutput = new CombineOutput(process);
            status = combineOutput.waitFor();
            output = combineOutput.output();

            process.destroy();
        } catch (Exception e) {
            e.printStackTrace();
            if (TextUtils.isEmpty(output))
                output = e.getMessage();
        }
        return new Result(status, output);
    }

    private static class CombineOutput {
        private final StringBuilder builder = new StringBuilder();
        private final Process process;

        public CombineOutput(Process process) {
            this.process = process;
            new DealProcessSteam(process.getInputStream(), this).start();
            new DealProcessSteam(process.getErrorStream(), this).start();
        }

        public synchronized void input(String input) {
            builder.append(input);
        }

        public synchronized String output() {
            return builder.toString();
        }

        public int waitFor() throws InterruptedException {
            return process.waitFor();
        }
    }

    private static class DealProcessSteam extends Thread {
        private final InputStream inputStream;
        private final CombineOutput combineOutput;

        public DealProcessSteam(InputStream inputStream, CombineOutput combineOutput) {
            this.inputStream = inputStream;
            this.combineOutput = combineOutput;
        }

        @Override
        public void run() {
            super.run();
            InputStream is = new BufferedInputStream(inputStream);
            int len;
            byte[] bytes = new byte[4 * 1024];

            try {
                while ((len = is.read(bytes)) != -1) {
                    combineOutput.input(new String(bytes, 0, len));
                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
