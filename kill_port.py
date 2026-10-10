import os
import subprocess
import sys

def kill_port(port):
    try:
        # Windows
        if os.name == 'nt':
            cmd = f'netstat -ano | findstr :{port}'
            result = subprocess.check_output(cmd, shell=True).decode()

            for line in result.strip().split('\n'):
                if str(port) in line:
                    parts = line.split()
                    pid = parts[-1]
                    print(f"找到 PID: {pid}，準備關掉...")
                    subprocess.call(f'taskkill /PID {pid} /F', shell=True)

        # Linux / macOS
        else:
            cmd = f"lsof -i :{port}"
            result = subprocess.check_output(cmd, shell=True).decode()

            for line in result.strip().split('\n')[1:]:
                parts = line.split()
                pid = parts[1]
                print(f"找到 PID: {pid}，準備關掉...")
                os.kill(int(pid), 9)

    except subprocess.CalledProcessError:
        print("沒有找到該 port 的使用程序 🤷")

if __name__ == "__main__":
    port = int(sys.argv[1])
    kill_port(port)