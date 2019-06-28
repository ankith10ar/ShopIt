import {Cart} from "../cart-table.component";

export class Carts implements Cart{
  private _cartid: string;
  private _cost: any;
  private _costofeach: any;
  private _desp: string;
  private _name: string;
  private _quantity: number;


  get cartid(): string {
    return this._cartid;
  }

  set cartid(value: string) {
    this._cartid = value;
  }

  get cost(): any {
    return this._cost;
  }

  set cost(value: any) {
    this._cost = value;
  }

  get costofeach(): any {
    return this._costofeach;
  }

  set costofeach(value: any) {
    this._costofeach = value;
  }

  get desp(): string {
    return this._desp;
  }

  set desp(value: string) {
    this._desp = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get quantity(): number {
    return this._quantity;
  }

  set quantity(value: number) {
    this._quantity = value;
  }
}
