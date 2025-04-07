/**
 * 校验用户名: 长度为6-12位的字母数字下划线(开头必须是字母)
 */
export function isUserName(str: string): boolean {
  return /^[a-zA-z]\w{5,12}$/.test(str);
}
/**
 * 校验密码 6-17位字母数字下划线
 */
export function isPassword(str: string): boolean {
  return /^\w{5,17}$/.test(str);
}
