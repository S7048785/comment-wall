import JSEncrypt from "jsencrypt";

const rsaKey = import.meta.env.VITE_RSA_PUBLIC_KEY;

// 创建实例
const encryptor = new JSEncrypt();
// 加密函数
export const encryptData = (text: string): string | false => {
  const encryptor = new JSEncrypt();
  encryptor.setPublicKey(rsaKey);
  return encryptor.encrypt(text);
};
