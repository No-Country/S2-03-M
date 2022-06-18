import { AdditionalService } from './additional-service.interface';
import { Fee } from './fee.interface';
export interface Price {
  additionalServices?: AdditionalService[];
  base: string;
  currency: string;
  fees?: Fee[];
  grandTotal?: number;
  total: number;
}
