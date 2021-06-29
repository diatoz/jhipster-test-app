export interface ISkill {
  id?: string;
  skill?: string | null;
  skillDesc?: string | null;
  icon?: string | null;
  language?: string | null;
  type?: string | null;
}

export const defaultValue: Readonly<ISkill> = {};
