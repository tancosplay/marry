import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Address;
import org.fisco.bcos.sdk.abi.datatypes.Bool;
import org.fisco.bcos.sdk.abi.datatypes.DynamicArray;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.abi.datatypes.generated.Int8;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint8;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple10;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple5;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class Proof extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610cac806100606000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c8063444d95b01161005b578063444d95b0146102615780638d885af5146103025780639f5beaea146103d7578063c0b05ded1461043f5761007d565b806331b8a6971461008257806337d8786c1461010557806340d871a81461018c575b600080fd5b6100ae6004803603602081101561009857600080fd5b81019080803590602001909291905050506104bd565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b838110156100f15780820151818401526020810190506100d6565b505050509050019250505060405180910390f35b610172600480360360a081101561011b57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803560ff16906020019092919080359060200190929190803590602001909291908035906020019092919050505061060f565b604051808215151515815260200191505060405180910390f35b61024560048036036101408110156101a357600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803560ff169060200190929190803590602001909291908035906020019092919080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803560ff1690602001909291908035906020019092919080359060200190929190803590602001909291905050506106c8565b604051808260000b60000b815260200191505060405180910390f35b61028d6004803603602081101561027757600080fd5b81019080803590602001909291905050506107b8565b604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390f35b6103bb600480360361014081101561031957600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803560ff169060200190929190803590602001909291908035906020019092919080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803560ff16906020019092919080359060200190929190803590602001909291908035906020019092919050505061081c565b604051808260000b60000b815260200191505060405180910390f35b610423600480360360408110156103ed57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610997565b604051808260000b60000b815260200191505060405180910390f35b6104a16004803603604081101561045557600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610a93565b604051808260000b60000b815260200191505060405180910390f35b606080600267ffffffffffffffff811180156104d857600080fd5b506040519080825280602002602001820160405280156105075781602001602082028036833780820191505090505b5090506001600084815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168160008151811061054e57fe5b602002602001019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250506001600084815260200190815260200160002060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16816001815181106105cc57fe5b602002602001019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff168152505080915050919050565b60008060018387878760405160008152602001604052604051808581526020018460ff1660ff1681526020018381526020018281526020019450505050506020604051602081039080840390855afa15801561066f573d6000803e3d6000fd5b5050506020604051035190508673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614156106b95760019150506106bf565b60009150505b95945050505050565b6000806106d53384610997565b90507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8160000b1461070a57809150506107aa565b60006107198d8d8d8d8d61060f565b8061072d575061072c888888888d61060f565b5b905080156107a35760016000858152602001908152602001600020600080820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556001820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055505081925050506107aa565b6007925050505b9a9950505050505050505050565b60016020528060005260406000206000915090508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905082565b6000806108293384610997565b905060018160000b1461083f5780915050610989565b600061084e8d8d8d8d8d61060f565b806108625750610861888888888d61060f565b5b905080156109825760405180604001604052808e73ffffffffffffffffffffffffffffffffffffffff1681526020018973ffffffffffffffffffffffffffffffffffffffff168152506001600086815260200190815260200160002060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555090505060028490806001815401808255809150506001900390600052602060002001600090919091909150558192505050610989565b6007925050505b9a9950505050505050505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146109f65760009050610a8d565b600073ffffffffffffffffffffffffffffffffffffffff166001600084815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614610a88577fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff9050610a8d565b600190505b92915050565b600080600280549050905060008090505b81811015610c2357610ab4610c30565b6001600060028481548110610ac557fe5b906000526020600020015481526020019081526020016000206040518060400160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152505090508573ffffffffffffffffffffffffffffffffffffffff16816000015173ffffffffffffffffffffffffffffffffffffffff161480610c0457508473ffffffffffffffffffffffffffffffffffffffff16816020015173ffffffffffffffffffffffffffffffffffffffff16145b15610c155760039350505050610c2a565b508080600101915050610aa4565b5060029150505b92915050565b6040518060400160405280600073ffffffffffffffffffffffffffffffffffffffff168152602001600073ffffffffffffffffffffffffffffffffffffffff168152509056fea264697066735822122098a1378316caac1f1f237f9d87b894c22c506663851846a719f06b886b47efab64736f6c634300060a0033"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"inputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"man\",\"type\":\"address\"},{\"internalType\":\"uint8\",\"name\":\"v\",\"type\":\"uint8\"},{\"internalType\":\"bytes32\",\"name\":\"r\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"s\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"hash_mess\",\"type\":\"bytes32\"},{\"internalType\":\"address\",\"name\":\"woman\",\"type\":\"address\"},{\"internalType\":\"uint8\",\"name\":\"v1\",\"type\":\"uint8\"},{\"internalType\":\"bytes32\",\"name\":\"r1\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"s1\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"proof\",\"type\":\"bytes32\"}],\"name\":\"add\",\"outputs\":[{\"internalType\":\"int8\",\"name\":\"\",\"type\":\"int8\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"man\",\"type\":\"address\"},{\"internalType\":\"uint8\",\"name\":\"v\",\"type\":\"uint8\"},{\"internalType\":\"bytes32\",\"name\":\"r\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"s\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"hash_mess\",\"type\":\"bytes32\"},{\"internalType\":\"address\",\"name\":\"woman\",\"type\":\"address\"},{\"internalType\":\"uint8\",\"name\":\"v1\",\"type\":\"uint8\"},{\"internalType\":\"bytes32\",\"name\":\"r1\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"s1\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"proof\",\"type\":\"bytes32\"}],\"name\":\"del\",\"outputs\":[{\"internalType\":\"int8\",\"name\":\"\",\"type\":\"int8\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"m\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"wm\",\"type\":\"address\"}],\"name\":\"exist\",\"outputs\":[{\"internalType\":\"int8\",\"name\":\"\",\"type\":\"int8\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"m\",\"type\":\"address\"},{\"internalType\":\"bytes32\",\"name\":\"proof\",\"type\":\"bytes32\"}],\"name\":\"judge_govern\",\"outputs\":[{\"internalType\":\"int8\",\"name\":\"\",\"type\":\"int8\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"address\",\"name\":\"man\",\"type\":\"address\"},{\"internalType\":\"uint8\",\"name\":\"v\",\"type\":\"uint8\"},{\"internalType\":\"bytes32\",\"name\":\"r\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"s\",\"type\":\"bytes32\"},{\"internalType\":\"bytes32\",\"name\":\"hash_mess\",\"type\":\"bytes32\"}],\"name\":\"judge_sig\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"name\":\"proofs\",\"outputs\":[{\"internalType\":\"address\",\"name\":\"man\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"woman\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"bytes32\",\"name\":\"proof\",\"type\":\"bytes32\"}],\"name\":\"search\",\"outputs\":[{\"internalType\":\"address[]\",\"name\":\"\",\"type\":\"address[]\"}],\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_ADD = "add";

    public static final String FUNC_DEL = "del";

    public static final String FUNC_EXIST = "exist";

    public static final String FUNC_JUDGE_GOVERN = "judge_govern";

    public static final String FUNC_JUDGE_SIG = "judge_sig";

    public static final String FUNC_PROOFS = "proofs";

    public static final String FUNC_SEARCH = "search";

    protected Proof(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt add(String man, BigInteger v, byte[] r, byte[] s, byte[] hash_mess, String woman, BigInteger v1, byte[] r1, byte[] s1, byte[] proof) {
        final Function function = new Function(
                FUNC_ADD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(man), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint8(v), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(r), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(s), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash_mess), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(woman), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint8(v1), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(r1), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(s1), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(proof)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] add(String man, BigInteger v, byte[] r, byte[] s, byte[] hash_mess, String woman, BigInteger v1, byte[] r1, byte[] s1, byte[] proof, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ADD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(man), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint8(v), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(r), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(s), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash_mess), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(woman), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint8(v1), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(r1), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(s1), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(proof)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForAdd(String man, BigInteger v, byte[] r, byte[] s, byte[] hash_mess, String woman, BigInteger v1, byte[] r1, byte[] s1, byte[] proof) {
        final Function function = new Function(
                FUNC_ADD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(man), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint8(v), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(r), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(s), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash_mess), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(woman), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint8(v1), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(r1), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(s1), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(proof)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple10<String, BigInteger, byte[], byte[], byte[], String, BigInteger, byte[], byte[], byte[]> getAddInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ADD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple10<String, BigInteger, byte[], byte[], byte[], String, BigInteger, byte[], byte[], byte[]>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (byte[]) results.get(2).getValue(), 
                (byte[]) results.get(3).getValue(), 
                (byte[]) results.get(4).getValue(), 
                (String) results.get(5).getValue(), 
                (BigInteger) results.get(6).getValue(), 
                (byte[]) results.get(7).getValue(), 
                (byte[]) results.get(8).getValue(), 
                (byte[]) results.get(9).getValue()
                );
    }

    public Tuple1<BigInteger> getAddOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_ADD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int8>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt del(String man, BigInteger v, byte[] r, byte[] s, byte[] hash_mess, String woman, BigInteger v1, byte[] r1, byte[] s1, byte[] proof) {
        final Function function = new Function(
                FUNC_DEL, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(man), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint8(v), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(r), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(s), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash_mess), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(woman), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint8(v1), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(r1), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(s1), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(proof)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] del(String man, BigInteger v, byte[] r, byte[] s, byte[] hash_mess, String woman, BigInteger v1, byte[] r1, byte[] s1, byte[] proof, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_DEL, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(man), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint8(v), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(r), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(s), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash_mess), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(woman), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint8(v1), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(r1), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(s1), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(proof)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForDel(String man, BigInteger v, byte[] r, byte[] s, byte[] hash_mess, String woman, BigInteger v1, byte[] r1, byte[] s1, byte[] proof) {
        final Function function = new Function(
                FUNC_DEL, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(man), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint8(v), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(r), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(s), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash_mess), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(woman), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint8(v1), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(r1), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(s1), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(proof)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple10<String, BigInteger, byte[], byte[], byte[], String, BigInteger, byte[], byte[], byte[]> getDelInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_DEL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple10<String, BigInteger, byte[], byte[], byte[], String, BigInteger, byte[], byte[], byte[]>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (byte[]) results.get(2).getValue(), 
                (byte[]) results.get(3).getValue(), 
                (byte[]) results.get(4).getValue(), 
                (String) results.get(5).getValue(), 
                (BigInteger) results.get(6).getValue(), 
                (byte[]) results.get(7).getValue(), 
                (byte[]) results.get(8).getValue(), 
                (byte[]) results.get(9).getValue()
                );
    }

    public Tuple1<BigInteger> getDelOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_DEL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int8>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt exist(String m, String wm) {
        final Function function = new Function(
                FUNC_EXIST, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(m), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(wm)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] exist(String m, String wm, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_EXIST, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(m), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(wm)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForExist(String m, String wm) {
        final Function function = new Function(
                FUNC_EXIST, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(m), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(wm)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, String> getExistInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_EXIST, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public Tuple1<BigInteger> getExistOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_EXIST, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int8>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt judge_govern(String m, byte[] proof) {
        final Function function = new Function(
                FUNC_JUDGE_GOVERN, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(m), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(proof)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] judge_govern(String m, byte[] proof, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_JUDGE_GOVERN, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(m), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(proof)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForJudge_govern(String m, byte[] proof) {
        final Function function = new Function(
                FUNC_JUDGE_GOVERN, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(m), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(proof)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, byte[]> getJudge_governInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_JUDGE_GOVERN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, byte[]>(

                (String) results.get(0).getValue(), 
                (byte[]) results.get(1).getValue()
                );
    }

    public Tuple1<BigInteger> getJudge_governOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_JUDGE_GOVERN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int8>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt judge_sig(String man, BigInteger v, byte[] r, byte[] s, byte[] hash_mess) {
        final Function function = new Function(
                FUNC_JUDGE_SIG, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(man), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint8(v), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(r), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(s), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash_mess)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] judge_sig(String man, BigInteger v, byte[] r, byte[] s, byte[] hash_mess, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_JUDGE_SIG, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(man), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint8(v), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(r), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(s), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash_mess)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForJudge_sig(String man, BigInteger v, byte[] r, byte[] s, byte[] hash_mess) {
        final Function function = new Function(
                FUNC_JUDGE_SIG, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(man), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint8(v), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(r), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(s), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(hash_mess)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple5<String, BigInteger, byte[], byte[], byte[]> getJudge_sigInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_JUDGE_SIG, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple5<String, BigInteger, byte[], byte[], byte[]>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (byte[]) results.get(2).getValue(), 
                (byte[]) results.get(3).getValue(), 
                (byte[]) results.get(4).getValue()
                );
    }

    public Tuple1<Boolean> getJudge_sigOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_JUDGE_SIG, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public Tuple2<String, String> proofs(byte[] param0) throws ContractException {
        final Function function = new Function(FUNC_PROOFS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple2<String, String>(
                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue());
    }

    public TransactionReceipt search(byte[] proof) {
        final Function function = new Function(
                FUNC_SEARCH, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(proof)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] search(byte[] proof, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SEARCH, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(proof)), 
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSearch(byte[] proof) {
        final Function function = new Function(
                FUNC_SEARCH, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(proof)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<byte[]> getSearchInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SEARCH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<byte[]>(

                (byte[]) results.get(0).getValue()
                );
    }

    public Tuple1<List<String>> getSearchOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SEARCH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<List<String>>(

                convertToNative((List<Address>) results.get(0).getValue())
                );
    }

    public static Proof load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new Proof(contractAddress, client, credential);
    }

    public static Proof deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(Proof.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }
}
