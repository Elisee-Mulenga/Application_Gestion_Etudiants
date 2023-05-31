export interface IDocument {
  id?: number;
  certificat?: string | null;
  piece?: string | null;
}

export const defaultValue: Readonly<IDocument> = {};
