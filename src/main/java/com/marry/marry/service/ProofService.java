package com.marry.marry.service;

import com.marry.marry.model.bo.ProofAddInputBO;
import com.marry.marry.model.bo.ProofDelInputBO;
import com.marry.marry.model.bo.ProofExistInputBO;
import com.marry.marry.model.bo.ProofJudge_governInputBO;
import com.marry.marry.model.bo.ProofJudge_sigInputBO;
import com.marry.marry.model.bo.ProofProofsInputBO;
import com.marry.marry.model.bo.ProofSearchInputBO;
import java.lang.Exception;
import java.lang.String;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Data
public class ProofService {
  public static final String ABI = com.marry.marry.utils.IOUtil.readResourceAsString("abi/Proof.abi");

  public static final String BINARY = com.marry.marry.utils.IOUtil.readResourceAsString("bin/ecc/Proof.bin");

  public static final String SM_BINARY = com.marry.marry.utils.IOUtil.readResourceAsString("bin/sm/Proof.bin");

  @Value("${system.contract.proofAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public TransactionResponse add(ProofAddInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "add", input.toArgs());
  }

  public CallResponse proofs(ProofProofsInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "proofs", input.toArgs());
  }

  public TransactionResponse exist(ProofExistInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "exist", input.toArgs());
  }

  public TransactionResponse judge_sig(ProofJudge_sigInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "judge_sig", input.toArgs());
  }

  public TransactionResponse del(ProofDelInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "del", input.toArgs());
  }

  public TransactionResponse search(ProofSearchInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "search", input.toArgs());
  }

  public TransactionResponse judge_govern(ProofJudge_governInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "judge_govern", input.toArgs());
  }
}
