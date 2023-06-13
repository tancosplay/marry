package com.marry.marry.model.bo;

import java.lang.Object;
import java.lang.String;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProofJudge_sigInputBO {
  private String man;

  private BigInteger v;

  private byte[] r;

  private byte[] s;

  private byte[] hash_mess;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(man);
    args.add(v);
    args.add(r);
    args.add(s);
    args.add(hash_mess);
    return args;
  }
}
