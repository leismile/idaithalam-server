package ch.inss.idaiserver.exception;

public class UnableProcessException extends  Exception {
    public UnableProcessException(){
      super();
    }
  public UnableProcessException(String msg){
    super(msg);
  }
}
