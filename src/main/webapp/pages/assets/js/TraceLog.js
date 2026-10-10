class TraceLogger {
    static _getCallerLocation(depth = 2) {
        const err = new Error();
        const stackLines = err.stack?.split("\n");

        // depth = 2 表示呼叫 TraceLogger.log 的上一層
        const line = stackLines?.[depth]?.trim();
        if (!line) return "unknown";

        const match = line.match(/at (.+?) \((.+?):(\d+):\d+\)/) ||
            line.match(/at (.+?):(\d+):\d+/);

        if (match) {
            if (match.length === 4) {
                const func = match[1];
                const lineNum = match[3];
                return `${func} [${lineNum}]`;
            } else if (match.length === 3) {
                const file = match[1];
                const lineNum = match[2];
                return `${file}:${lineNum}`;
            }
        }
        return "unknown";
    }

    static log(...args) {
        const location = this._getCallerLocation(3);
        console.log(`🕵️ [${location}]`, ...args);
    }

    static warn(...args) {
        const location = this._getCallerLocation(3);
        console.warn(`⚠️ [${location}]`, ...args);
    }

    static error(...args) {
        const location = this._getCallerLocation(3);
        console.error(`💥 [${location}]`, ...args);
    }

    static debug(...args) {
        const location = this._getCallerLocation(3);
        console.debug(`🐞 [${location}]`, ...args);
    }
}
