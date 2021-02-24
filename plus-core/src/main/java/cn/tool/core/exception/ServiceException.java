package cn.tool.core.exception;

import cn.tool.core.common.ICodeStatus;

public class ServiceException extends RuntimeException {

    private ICodeStatus codeStatus;

    private static final long serialVersionUID = 3583566093089790852L;

    public ServiceException() {
        super();
    }

    public ServiceException(String code, String message) {
        super(message);
        this.codeStatus = new ServiceCodeStatus(code, message);
    }

    public ServiceException(String code) {
        super(code);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(ICodeStatus codeStatus) {
        this(codeStatus.getReasonPhrase());
        this.codeStatus = codeStatus;
    }

    public ICodeStatus getCodeStatus() {
        return this.codeStatus;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public class ServiceCodeStatus implements ICodeStatus {
        private String value;
        private String reasonPhrase;

        private ServiceCodeStatus(String value, String reasonPhrase) {
            this.value = value;
            this.reasonPhrase = reasonPhrase;
        }

        @Override
        public String value() {
            return this.value;
        }

        @Override
        public String getReasonPhrase() {
            return this.reasonPhrase;
        }
    }
}
