export class User {
  constructor(
   public id: String,
   public enable?: boolean,
   public name?: String,
   public vkid?: number,
   public countMassenges?: number,
   public icon?: String,
   public blockTypeCoubs?: Array<object>,
   public sentMessages?: Array<object>,
   public sentCoubsID?: Array<object>,
   public password?: String) {}
}
