package com.marry.marry.model.bo;

import java.lang.Object;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProofSearchInputBO {
  private byte[] proof;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(proof);
    return args;
  }
}
