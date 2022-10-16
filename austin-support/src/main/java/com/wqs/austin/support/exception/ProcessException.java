package com.wqs.austin.support.exception;

import com.wqs.austin.common.enums.RespStatusEnum;
import com.wqs.austin.support.pipeline.ProcessContext;

/**
 * author: wqs
 * date: 2022/10/15 17:35
 */
public class ProcessException extends RuntimeException{

    /**
     * 流程处理上下文
     */
    private final ProcessContext processContext;

    public ProcessException(ProcessContext processContext){
        super();
        this.processContext = processContext;
    }

    public ProcessException(ProcessContext processContext, Throwable cause){
        super(cause);
        this.processContext = processContext;
    }

    @Override
    public String getMessage() {
        if (this.processContext != null) {
            return this.processContext.getResponse().getMsg();
        } else {
            return RespStatusEnum.CONTEXT_IS_NULL.getMsg();
        }
    }

    public ProcessContext getProcessContext(){
        return processContext;
    }

}
