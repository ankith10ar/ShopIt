import {Cart} from "../cart-table.component";

export class CartProd {

  private _listCart: Cart[] = [];


  get listCart(): Cart[] {
    return this._listCart;
  }

  set listCart(value: Cart[]) {
    this._listCart = value;
  }
}
